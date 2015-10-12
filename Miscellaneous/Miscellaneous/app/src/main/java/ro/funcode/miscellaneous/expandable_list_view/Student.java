package ro.funcode.miscellaneous.expandable_list_view;

/**
 * Created by catalinprata on 12/10/15.
 */
public class Student {

    private String mName;
    private int mAge;

    public Student(String name, int age) {
        mName = name;
        mAge = age;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public int getAge() {
        return mAge;
    }

    public void setAge(int age) {
        mAge = age;
    }
}
