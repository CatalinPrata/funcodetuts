package com.realmreset.sample;

import android.content.Intent;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class Activity2 extends ASuperActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.new_activity)
    void openNewActivity() {
        startActivity(new Intent(Activity2.this, Activity3.class));
    }
}
