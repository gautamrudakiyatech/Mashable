package com.ma.ectsmpm.mashable;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.anythink.core.api.ATSDK;
import com.facebook.ads.AudienceNetworkAds;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // AudienceNetwork init
        AudienceNetworkAds.initialize(this);
        // ATSDK init
        ATSDK.init(this, "a666806b442bc0", "a3ce4d63dd6e2392660ed3a2684cc3176");

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(getApplicationContext(), Activity1.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
        }, 1200);

    }

}