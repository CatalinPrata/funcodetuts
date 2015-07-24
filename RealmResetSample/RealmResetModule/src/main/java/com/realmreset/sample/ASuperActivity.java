package com.realmreset.sample;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import io.realm.Realm;

public class ASuperActivity extends Activity {

    protected Realm mRealm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mRealm = Realm.getInstance(this);

    }

    @Override
    protected void onDestroy() {

        mRealm.close();

        super.onDestroy();
    }
}
