package ro.funcode.gsonserializer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GsonBuilder gsonBuilder = new GsonBuilder();
        // make sure we use the custom Deserializer
        gsonBuilder.registerTypeAdapter(Sample.class, new SampleGSONParserAdapter());

        Gson gson = gsonBuilder.create();
        Sample sample = gson.fromJson("{\n" +
                "    \"name\": \"some name\",\n" +
                "    \"aNumber\": 233,\n" +
                "    \"data\": {\n" +
                "        \"aproperty\": \"aValue\",\n" +
                "        \"subObject\": {\n" +
                "            \"property\": \"otherValue\"\n" +
                "        }\n" +
                "    }\n" +
                "}", Sample.class);

        Log.e("test", sample.getData());

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
