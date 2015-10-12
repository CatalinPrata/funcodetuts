package ro.funcode.miscellaneous.expandable_list_view;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import ro.funcode.miscellaneous.R;

/**
 * Created by catalinprata on 12/10/15.
 */
public class ExpandableListViewAdapter extends BaseExpandableListAdapter {

    /**
     * The classes will represent the group items and the students from each class will represent the child of each group
     */
    private ArrayList<ClassRoom> mClassRooms;
    /**
     * Keep this so we won't have to recreate one each time we need to inflate a view
     */
    private LayoutInflater mLayoutInflater;

    public ExpandableListViewAdapter(ArrayList<ClassRoom> classRooms, Context context) {
        mClassRooms = classRooms;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getGroupCount() {
        return mClassRooms.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        // return the number of students for the requested class since we agreed that
        // the child view of the group is represented by the students of a class
        return mClassRooms.get(groupPosition).numberOfStudents();
    }

    @Override
    public ClassRoom getGroup(int groupPosition) {
        return mClassRooms.get(groupPosition);
    }

    @Override
    public Student getChild(int groupPosition, int childPosition) {
        return mClassRooms.get(groupPosition).getStudents().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        ClassViewHolder classViewHolder;

        if (convertView == null){
            classViewHolder = new ClassViewHolder();
            convertView = mLayoutInflater.inflate(R.layout.expandable_list_item_parent, parent, false);
            classViewHolder.name = (TextView) convertView.findViewById(R.id.parent_name);
            classViewHolder.numberOfStudents = (TextView) convertView.findViewById(R.id.number_of_students);
            classViewHolder.deleteButton = (Button) convertView.findViewById(R.id.delete_parent_btn);
            classViewHolder.deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    // remember that the tag is set to the group position,
                    // now we just have to get it and remove the group.
                    mClassRooms.remove((int) v.getTag());

                    notifyDataSetChanged();
                }
            });

            convertView.setTag(classViewHolder);

        } else {
            classViewHolder = (ClassViewHolder) convertView.getTag();
        }

        ClassRoom classRoom = getGroup(groupPosition);

        classViewHolder.name.setText("Class room " + classRoom.getName());
        classViewHolder.numberOfStudents.setText("No. of students: " + classRoom.numberOfStudents());

        // keep the group position on the delete view to get it later in onClick method
        classViewHolder.deleteButton.setTag(groupPosition);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder studentViewHolder;

        if (convertView == null){
            studentViewHolder = new ChildViewHolder();
            convertView = mLayoutInflater.inflate(R.layout.expandable_list_item_child, parent, false);
            studentViewHolder.name = (TextView) convertView.findViewById(R.id.child_name);
            studentViewHolder.age = (TextView) convertView.findViewById(R.id.age);
            studentViewHolder.deleteButton = (Button) convertView.findViewById(R.id.delete_child_btn);
            studentViewHolder.deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    ChildViewHolder childHolder = (ChildViewHolder) v.getTag();
                    // remove the clicked item from the list and refresh the UI
                    getGroup(childHolder.groupPosition).getStudents().remove(childHolder.childPosition);

                    notifyDataSetChanged();

                }
            });

            convertView.setTag(studentViewHolder);

        } else {
            studentViewHolder = (ChildViewHolder) convertView.getTag();
        }

        Student student = getChild(groupPosition, childPosition);

        studentViewHolder.name.setText(student.getName());
        studentViewHolder.age.setText("Age: " + student.getAge());

        studentViewHolder.childPosition = childPosition;
        studentViewHolder.groupPosition = groupPosition;

        // keep the studentViewHolder on the delete view to get it later in onClick method
        studentViewHolder.deleteButton.setTag(studentViewHolder);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {
        super.registerDataSetObserver(observer);
    }

    private class ClassViewHolder {
        TextView name;
        TextView numberOfStudents;
        Button deleteButton;
    }

    private class ChildViewHolder {
        TextView name;
        TextView age;
        Button deleteButton;
        int groupPosition;
        int childPosition;
    }
}
