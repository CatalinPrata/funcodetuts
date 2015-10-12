package com.realmreset.sample;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.jakewharton.processphoenix.ProcessPhoenix;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class Activity3 extends ASuperActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.restart)
    void restart() {
        final Intent nextIntent = new Intent(this, Activity3.class);

        mRealm.close();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                ProcessPhoenix.triggerRebirth(Activity3.this);

            }
        }, 3000);
    }

    @OnClick(R.id.restart_with_intent)
    void restartWithIntent() {


    }
}
