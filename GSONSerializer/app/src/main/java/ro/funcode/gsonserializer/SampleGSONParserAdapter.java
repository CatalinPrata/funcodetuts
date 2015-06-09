package ro.funcode.gsonserializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by catalinprata on 10/06/15.
 *
 * Deserializes the Sample object by keeping the jsonString JSON value as a string instead of parsing that too.
 */
public class SampleGSONParserAdapter implements
        JsonDeserializer<Sample> {

    @Override
    public Sample deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        Sample sample = new Sample();
        JsonObject sampleJsonObject = json.getAsJsonObject();

        sample.setaNumber(sampleJsonObject.get("aNumber").getAsInt());
        sample.setName(sampleJsonObject.get("name").getAsString());

        // get the data object as a string
        sample.setData(sampleJsonObject.get("data").toString());

        return sample;
    }

}
