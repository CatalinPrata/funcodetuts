package ro.funcode.androidlocalizing;

import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // the filename is important! This should be used to fetch the localized strings as well!!
        SharedPreferences settings = getSharedPreferences("localization_strings_file", 0);
        SharedPreferences.Editor editor = settings.edit();

        // add some translations to SharedPreferences
        editor.putString("ro_hello_world", "Salutare lume!");
        editor.putString("en_hello_world", "Hello world!");

        // Commit the edits!
        editor.commit();

        // now we can load the text
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
