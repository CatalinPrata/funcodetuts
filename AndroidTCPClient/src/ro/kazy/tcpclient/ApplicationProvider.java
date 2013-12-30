package ro.kazy.tcpclient;

import android.app.Application;
import android.content.Context;

/**
 * Created by catalin on 12/29/13.
 */
public class ApplicationProvider extends Application {

    private static Context sApplicationContext;

    @Override
    public void onCreate() {
        super.onCreate();

        sApplicationContext = getApplicationContext();

    }

    public static Context getContext() {
        return sApplicationContext;
    }
}
