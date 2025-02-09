package com.ma.ectsmpm.mashable;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

/** @noinspection ALL*/
public class Activity2 extends AppCompatActivity {

    String fulldata, tag, image, titl, te2, te3, te4, imagecredit;
    WebView webView;
    String e, a, b, c, d, f;
    ProgressDialog progressDialog;
    ImageView imageView;
    TextView title, text2, text3, text;

    @SuppressLint({"MissingInflatedId", "SetJavaScriptEnabled"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);


        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait while loading data..");
        progressDialog.setCancelable(false);
        progressDialog.show();

        webView = findViewById(R.id.webView);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient());

        Intent getdata = getIntent();
        a = getdata.getStringExtra("title");
        e = getdata.getStringExtra("postLink");
        f = getdata.getStringExtra("image");

        imageView = findViewById(R.id.imageView);
        text = findViewById(R.id.text);

        Content content = new Content();
        content.execute();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeActionContentDescription("Back");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

    }

    @SuppressLint("StaticFieldLeak")
    public class Content extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            webView.setWebChromeClient(new WebChromeClient());

            // Apply CSS styling and construct HTML content
            String htmlContent = "<html><head><style>" +
                    "body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; line-height: 1.6; padding: 12px; background-color: #fff1fff; color: #333; }" +
                    ".content { margin-bottom: 25px; background-color: #fff; border-radius: 16px; padding: 16px; box-shadow: 4px 6px 10px rgba(0.8,0.8,0.8,0.5); }" +
                    ".entry-title { font-size: 20px; margin-bottom: 10px; color: #007bff; }" +
                    ".entry-meta { font-style: italic; margin-bottom: 10px; color: #666; }" +
                    ".entry-meta span { margin-right: 10px; }" +
                    ".entry-content { margin-top: 20px; color: #000; font-size: 18px; }" +
                    ".meta-tags { margin-top: 20px; color: #000;}" +
                    ".meta-tags span { display: inline-block; background-color: #007bff; color: #000; padding: 5px 10px; border-radius: 10px; margin-right: 10px; margin-left: 10px; margin-bottom: 10px; }" +
                    "a { color: #000000; }" + // Added CSS rule for links
                    "</style></head><body>" +
                    "<div class=\"content\">" +
                    "<h6 class=\"entry-meta\">" + imagecredit + "</h6>" +
                    "<h1 class=\"entry-title\">" + titl + "</h1>" +
                    "<div class=\"entry-meta\">" +
                    "<span class=\"meta-date\">" + te2 + "</span>" +
                    "<span class=\"meta-categories\">" + te4 + "</span>" +
                    "</div>" +
                    "<div class=\"entry-content\">" + fulldata + "</div>" +
                    "</div>" +
                    "</body>" +
                    "</html>";


            webView.loadDataWithBaseURL(null, htmlContent, "text/html", "utf-8", null);
            webView.requestFocus();

            Picasso.get().load(f).into(imageView);

            title = findViewById(R.id.title);
            if (a == null) {
                title.setText("----");
            } else {
                title.setText(a);
            }

            text2 = findViewById(R.id.text2);
            if (te2 == null) {
                text2.setText("----");
            } else {
                text2.setText(te2);
                text2.setSelected(true);
            }

            progressDialog.dismiss();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Document doc = Jsoup.connect(e).get();

                Elements data = doc.select("div.wrapper");

                int i = 0;
                fulldata = data.select("div.text-content").eq(i).html();

                titl = data.select("h1").eq(i).text();
                imagecredit = data.select("span.credits").eq(i).text();
                te2 = data.select("time").eq(i).text();
                te4 = data.select(".author-names").eq(i).text();

                image = data.select("div.image")
                        .select("img")
                        .eq(i)
                        .attr("src");

                Log.d("items", "data: " + e);
            } catch (IOException e) {
                Log.e("ContentAsyncTask", "Error fetching data", e);
                progressDialog.dismiss();
                return null;
            }
            return null;
        }

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(getApplicationContext(), Activity1.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right_1, R.anim.slide_in_left_1);
        finish();
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), Activity1.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right_1, R.anim.slide_in_left_1);
        finish();
    }

}