package ro.funcode.miscellaneous.expandable_list_view;

import java.util.ArrayList;

/**
 * Created by catalinprata on 12/10/15.
 */
public class ClassRoom {

    private ArrayList<Student> mStudents;
    private String mName;

    public ClassRoom(ArrayList<Student> students, String name) {
        mStudents = students;
        mName = name;
    }

    public int numberOfStudents() {
        return mStudents == null ? 0 : mStudents.size();
    }

    public ArrayList<Student> getStudents() {
        return mStudents;
    }

    public void setStudents(ArrayList<Student> students) {
        mStudents = students;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }
}
