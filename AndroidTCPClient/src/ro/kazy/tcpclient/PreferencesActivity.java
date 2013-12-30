package ro.kazy.tcpclient;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by catalin on 12/29/13.
 */
public class PreferencesActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preferences);

    }



}
