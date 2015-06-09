package ro.funcode.gsonserializer;

/**
 * Created by catalinprata on 09/06/15.
 *
 * We have a JSON like this one:
 * {
    "name": "some name",
    "aNumber": 233,
    "data": {
        "aproperty": "aValue",
        "subObject": {
            "property": "otherValue"
        }
    }
    }
 *
 */
public class Sample {

    private String name;
    private int aNumber;
    // keeps the String representation of the JSON object named "data"
    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getaNumber() {
        return aNumber;
    }

    public void setaNumber(int aNumber) {
        this.aNumber = aNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
