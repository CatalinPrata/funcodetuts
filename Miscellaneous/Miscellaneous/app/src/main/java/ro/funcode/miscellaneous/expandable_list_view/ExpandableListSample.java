package ro.funcode.miscellaneous.expandable_list_view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;

import ro.funcode.miscellaneous.R;

public class ExpandableListSample extends AppCompatActivity {

    private ExpandableListView mExpandableListView;
    private ExpandableListViewAdapter mExpandableListViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable_list_sample);

        mExpandableListView = (ExpandableListView) findViewById(R.id.expandableListView);

        mExpandableListViewAdapter = new ExpandableListViewAdapter(generateDataSet(), this);
        mExpandableListView.setAdapter(mExpandableListViewAdapter);

        mExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                Student student = mExpandableListViewAdapter.getChild(groupPosition, childPosition);

                Toast.makeText(ExpandableListSample.this, "You pressed student :" + student.getName()
                        + ", age " + student.getAge(), Toast.LENGTH_SHORT).show();

                return false;
            }
        });

        mExpandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

                Toast.makeText(ExpandableListSample.this, "You pressed class no.:" + groupPosition
                        + " named: " + mExpandableListViewAdapter.getGroup(groupPosition).getName(), Toast.LENGTH_SHORT).show();

                return false;
            }
        });

    }

    private ArrayList<ClassRoom> generateDataSet() {

        ArrayList<ClassRoom> classRooms = new ArrayList<>();

        for (int index = 0; index < 10; index++) {

            classRooms.add(new ClassRoom(generateStudents(), "Panda " + index));

        }

        return classRooms;
    }

    private ArrayList<Student> generateStudents() {

        ArrayList<Student> students = new ArrayList<>();

        for (int index = 0; index < 8; index++) {
            students.add(new Student("Student " + index, index + 19));
        }

        return students;
    }
}
