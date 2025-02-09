package com.ma.ectsmpm.mashable;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.ColorUtils;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ParseAdapter extends RecyclerView.Adapter<ParseAdapter.ViewHolder> {

    private ArrayList<ParseItem> parseItems;
    private final Context context;
    private final Activity activity;
    private final int lastposition = -1;

    public ParseAdapter(ArrayList<ParseItem> parseItems, Context context, Activity activity) {
        this.parseItems = parseItems;
        this.context = context;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ParseItem parseItem = parseItems.get(position);
        holder.textView.setText(parseItem.getTitle());
        holder.textView1.setText(parseItem.getDesc());
        holder.textView2.setText(parseItem.getDate());
        holder.textView3.setText(parseItem.getAuthor());
//        Picasso.get().load(parseItem.getImgUrl()).into(holder.imageView);
        try {
            if (parseItem.getImgUrl() == null || parseItem.getImgUrl().isEmpty()) {
                // Set a default drawable image
//                holder.imageView.setImageResource(R.drawable.noimage); // Replace default_image with the actual resource ID of your drawable
                holder.imagecard.setStrokeColor(Color.WHITE);
                holder.imagecard.setElevation(0);
            } else {
                // Load the image using Picasso
                Picasso.get().load(parseItem.getImgUrl()).into(holder.imageView);
                setAnimationImage(holder.imageView, position);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        holder.card.setOnClickListener(v -> {

            if (parseItem.getTitle() == null || parseItem.getTitle().isEmpty()) {
                holder.card.setEnabled(false);
                Toast.makeText(context, "Content is not available..", Toast.LENGTH_SHORT).show();
            } else {
                holder.card.setEnabled(true);
                Intent intent = new Intent(context, Activity2.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                intent.putExtra("title", parseItem.getTitle());
                intent.putExtra("image", parseItem.getImgUrl());
                intent.putExtra("postLink", parseItem.getPostLink());
                intent.putExtra("author", parseItem.getAuthor());
                intent.putExtra("date", parseItem.getDate());
                intent.putExtra("cate", parseItem.getCategories());
                activity.startActivity(intent);
                activity.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_in_right);
            }

        });

        Animation slideInAnimation = AnimationUtils.loadAnimation(holder.itemView.getContext(), R.anim.ip);
        holder.card.startAnimation(slideInAnimation);

//
//        int[] colorBackgrounds = {R.color.A, R.color.B, R.color.C, R.color.D, R.color.E, R.color.F, R.color.G, R.color.H};
//
//        int colorResourceId = colorBackgrounds[position % colorBackgrounds.length];
//        int color = ContextCompat.getColor(holder.itemView.getContext(), colorResourceId);
//        holder.card.setStrokeColor(color);
//
//        setAnimation(holder.itemView, position);

    }

    @Override
    public int getItemCount() {
        return parseItems.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void updateList(ArrayList<ParseItem> newList) {
        parseItems = newList;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView, textView1, textView2, textView3, textView4;
        MaterialCardView card, imagecard;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            imagecard = itemView.findViewById(R.id.imagecard);
            textView = itemView.findViewById(R.id.text);
            textView1 = itemView.findViewById(R.id.text1);
            textView2 = itemView.findViewById(R.id.text2);
            textView3 = itemView.findViewById(R.id.text3);
//            textView4 = itemView.findViewById(R.id.text4);
            textView3.setSelected(true);

            card = itemView.findViewById(R.id.card);

        }


    }

    private void setAnimation(View viewAnimate, int position) {

//        if (position > lastposition) {
        Animation slideIn = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
        viewAnimate.startAnimation(slideIn);
//            lastposition = position;
//        }

    }

    private void setAnimationImage(View viewAnimate, int position) {
        Animation slideIn = AnimationUtils.loadAnimation(context, android.R.anim.slide_out_right);
        viewAnimate.startAnimation(slideIn);
    }


}
