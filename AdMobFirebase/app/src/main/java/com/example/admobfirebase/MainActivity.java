package com.example.admobfirebase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(getApplicationContext(), "YOUR UNIT ID");

        AdView adView = (AdView) this.findViewById(R.id.adMob);
        //request TEST ads to avoid being disabled for clicking your own ads
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)// This is for emulators
                //test mode on DEVICE (this example code must be replaced with your device uniquq ID)
//                .addTestDevice("2EAB96D84FE62876379A9C030AA6A0AC") // Nexus 5
                .build();
        adView.loadAd(adRequest);
    }
}
