package ro.funcode.gsonserializer;

/**
 * Created by catalinprata on 09/06/15.
 */
public class Sample {

    private String aStringProperty;
    private int aNumber;
    private String jsonString; // "{ \"$type\": ... }"

    public String getJsonString() {
        return jsonString;
    }

    public void setJsonString(String jsonString) {
        this.jsonString = jsonString;
    }

    public int getaNumber() {
        return aNumber;
    }

    public void setaNumber(int aNumber) {
        this.aNumber = aNumber;
    }

    public String getaStringProperty() {
        return aStringProperty;
    }

    public void setaStringProperty(String aStringProperty) {
        this.aStringProperty = aStringProperty;
    }
}
