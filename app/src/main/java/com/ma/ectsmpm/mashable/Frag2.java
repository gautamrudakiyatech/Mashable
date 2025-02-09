package com.ma.ectsmpm.mashable;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.ma.ectsmpm.mashable.databinding.FragmentFrag2Binding;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @noinspection deprecation, CallToPrintStackTrace
 */
public class Frag2 extends Fragment {

    private ParseAdapter adapter;
    private final ArrayList<ParseItem> parseItems = new ArrayList<>();
    private SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;
    ShimmerFrameLayout shimmer;
    private static final String TECH_ROZEN_URL = "https://in.mashable.com/entertainment/";
    SearchView searchView;

    private FragmentFrag2Binding binding; // View Binding

    public static Frag2 newInstance() {
        return new Frag2();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFrag2Binding.inflate(inflater, container, false);
        View root = binding.getRoot();

        bindViews();

        searchView = root.findViewById(R.id.search);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false; // We handle filtering in real-time as the user types
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return true;
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        adapter = new ParseAdapter(parseItems, requireActivity(), requireActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.setVisibility(View.GONE);

        requireActivity().overridePendingTransition(R.anim.slide_in_left, R.anim.slide_in_right);

        swipeRefreshLayout.setOnRefreshListener(this::loadContent);

        // Initial data load
        loadContent();

        return root;
    }

    private void filter(String text) {
        ArrayList<ParseItem> filteredList = new ArrayList<>();

        for (ParseItem item : parseItems) {
            if (item.getTitle().toLowerCase().contains(text.toLowerCase()) ||
                    item.getDesc().toLowerCase().contains(text.toLowerCase()) ||
                    item.getDate().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }

        adapter.updateList(filteredList);
    }


    private void bindViews() {
        shimmer = binding.shimmer;
        shimmer.setVisibility(View.VISIBLE);

        swipeRefreshLayout = binding.refresh;
        recyclerView = binding.recyclerView;
    }

    private void loadContent() {
        shimmer.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        new Content().execute(TECH_ROZEN_URL);
    }

    /**
     * @noinspection CallToPrintStackTrace
     */
    @SuppressLint("StaticFieldLeak")
    public class Content extends AsyncTask<String, Void, ArrayList<ParseItem>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            shimmer.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
            shimmer.startAnimation(AnimationUtils.loadAnimation(requireActivity(), android.R.anim.fade_in));
        }

        @SuppressLint("NotifyDataSetChanged")
        @Override
        protected void onPostExecute(ArrayList<ParseItem> result) {
            super.onPostExecute(result);
            shimmer.setVisibility(View.GONE);
            shimmer.startAnimation(AnimationUtils.loadAnimation(requireActivity(), android.R.anim.fade_out));
            recyclerView.setVisibility(View.VISIBLE);

            // Clear the existing data and add the new items
            parseItems.clear();
            if (result != null) {
                parseItems.addAll(result);
            }

            adapter.notifyDataSetChanged();

            // Hide the refresh icon
            swipeRefreshLayout.setRefreshing(false);
        }

        @Override
        protected ArrayList<ParseItem> doInBackground(String... urls) {
            ArrayList<ParseItem> newItems = new ArrayList<>();

            // Check if urls array is not empty
            if (urls != null && urls.length > 0) {
                try {
                    Document doc = Jsoup.connect(urls[0]).get();
                    Elements data = doc.select("li.blogroll");

                    for (int i = 0; i < data.size(); i++) {
                        String imgUrl = "";

                        // Attempt to get the URL from the srcset attribute
                        String srcset = data.eq(i).select("img").attr("src");
                        if (!srcset.isEmpty()) {
                            String[] srcsetUrls = srcset.split(",");
                            if (srcsetUrls.length > 0) {
                                imgUrl = srcsetUrls[srcsetUrls.length - 1].trim().split(" ")[0];
                            }
                        }

                        // If srcset is empty, fallback to src attribute
                        if (imgUrl.isEmpty()) {
                            imgUrl = data.eq(i).select("img").attr("src");
                        }


                        String title = data.select("div.caption").eq(i).text();
                        String desc = data.select("div.deck").eq(i).text();
                        String date = data.select("time.datepublished").eq(i).text();
                        String postLink = data.select("a").eq(i).attr("href");

                        newItems.add(new ParseItem(imgUrl, title, desc, date, "cate", postLink, "author"));
                        Log.d("items", "img: " + imgUrl + " , pl: " + postLink);
                    }

            } catch(IOException e){
                e.printStackTrace();
                // Show error message if there's an IOException
                showErrorToast();
            } catch(IndexOutOfBoundsException e){
                e.printStackTrace();
                // Show error message if there's an IndexOutOfBoundsException
                showErrorToast();
            }
        }

            return newItems;
    }

    // Helper method to show error toast
    private void showErrorToast() {
        if (getActivity() != null) {
            getActivity().runOnUiThread(() ->
                    Toast.makeText(getActivity(), "Failed to load content. Please try again later.", Toast.LENGTH_SHORT).show());
        }
    }

}

}
