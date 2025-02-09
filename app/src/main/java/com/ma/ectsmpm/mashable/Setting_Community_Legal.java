package com.ma.ectsmpm.mashable;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class Setting_Community_Legal extends AppCompatActivity {

    @SuppressLint({"MissingInflatedId", "LocalSuppress", "SetJavaScriptEnabled"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_community_legal);

        WebView webView = findViewById(R.id.webView);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Set WebViewClient to handle errors
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                // Log error details
                Log.e("WebView Error", "Error Code: " + errorCode + ", Description: " + description);
            }
        });

        String htmlContent = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>NewzBeat - Legal Information</title>\n" +
                "    <style>\n" +
                "        body {\n" +
                "            font-family: Arial, sans-serif;\n" +
                "            line-height: 1.6;\n" +
                "            padding: 20px;\n" +
                "            color: #000000;\n" +
                "            background-color: #ffffff;\n" +
                "        }\n" +
                "\n" +
                "        h1 {\n" +
                "            font-size: 24px;\n" +
                "            color: #007bff;\n" +
                "        }\n" +
                "\n" +
                "        h2 {\n" +
                "            font-size: 20px;\n" +
                "            color: #007bff;\n" +
                "        }\n" +
                "\n" +
                "        p {\n" +
                "            margin-bottom: 15px;\n" +
                "        }\n" +
                "\n" +
                "        ul {\n" +
                "            margin-bottom: 15px;\n" +
                "            padding-left: 20px;\n" +
                "        }\n" +
                "\n" +
                "        a {\n" +
                "            color: #000000;\n" +
                "            text-decoration: none;\n" +
                "        }\n" +
                "\n" +
                "        a:hover {\n" +
                "            text-decoration: underline;\n" +
                "        }\n" +
                "\n" +
                "        .disclaimer {\n" +
                "            margin-top: 20px;\n" +
                "            background-color: #333333;\n" +
                "            color: #FFFFFF;\n" +
                "            border: 1px solid #000000;\n" +
                "            border-radius: .25rem;\n" +
                "            padding: 10px;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "<div>\n" +
                "    <h1>Mashable Privacy Policy</h1>\n" +
                "    <p>Your privacy is important to us. This Privacy Policy explains how we collect, use, disclose, and safeguard your information when you use our mobile application.</p>\n" +
                "    <h2>Collection of Information</h2>\n" +
                "    <p>We do not collect any personal information from our users. However, we may collect non-personal information automatically when you use the application.</p>\n" +
                "    <h2>Use of Information</h2>\n" +
                "    <p>We use the information we collect to analyze trends, administer the app, improve our products and services, and gather demographic information about our user base.</p>\n" +
                "    <h2>Sharing of Information</h2>\n" +
                "    <p>We do not sell, trade, or rent users' personal identification information to others.</p>\n" +
                "    <h2>Legal</h2>\n" +
                "    <p>This application is provided “as is” without any representations or warranties, express or implied.</p>\n" +
                "    <p>All content within this application is sourced from <a href=\"https://in.mashable.com/\" style=\"color:#007bff\">Mashable</a>. We do not have any authority over this content. If in.mashable.com requests the removal of their content, we will comply immediately.</p>\n" +
                "    <h2>Community Guidelines</h2>\n" +
                "    <ul>\n" +
                "        <li>Respect others and their opinions.</li>\n" +
                "        <li>Avoid posting offensive, inflammatory, or inappropriate content.</li>\n" +
                "        <li>Do not engage in harassment, bullying, or hate speech.</li>\n" +
                "        <li>Report any violations of these guidelines.</li>\n" +
                "    </ul>\n" +
                "    <p>For any questions about our Privacy Policy, Legal Information, or Community Guidelines, please <a href=\"mailto:shreeram.raghupati@gmail.com\">contact us</a>.</p>\n" +
                "    <div class=\"disclaimer\">\n" +
                "        <p><strong>Disclaimer:</strong> This app is provided for informational purposes only. The data available in this app may not be accurate, reliable, or trustworthy. We do not recommend or endorse the use of this app. The content provided in this app is sourced from various third-party websites, such as <a href=\"https://in.mashable.com/\" style=\"color:#007bff\">Mashable</a>, and may not always be up-to-date or presented in a consistent manner. Users are advised to use their discretion and verify information from multiple sources before relying on it.</p>\n" +
                "    </div>\n" +
                "</div>\n" +
                "</body>\n" +
                "</html>\n";

        // Load HTML content into WebView
        webView.loadDataWithBaseURL(null, htmlContent, "text/html", "UTF-8", null);

        Toolbar toolbar = findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeActionContentDescription("Back");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(getApplicationContext(), Activity1.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right_1, R.anim.slide_in_left_1);
        return super.onOptionsItemSelected(item);
    }

    /**
     * @noinspection deprecation
     */
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