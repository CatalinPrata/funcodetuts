package ro.kazy.tcpclient;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Used to load username from the preferences
 *
 * Created by catalin on 12/29/13.
 */
public class PreferencesManager {

    private static PreferencesManager ourInstance = new PreferencesManager();
    private SharedPreferences mPreferenceManager;

    public static PreferencesManager getInstance() {
        return ourInstance;
    }

    private PreferencesManager() {

        mPreferenceManager = PreferenceManager.getDefaultSharedPreferences(ApplicationProvider.getContext());

    }

    public String getUserName(){
        return mPreferenceManager.getString("pref_username", null);
    }

}
