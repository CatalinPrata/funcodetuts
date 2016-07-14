package com.example.navigationviewtabs;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class SimpleRecyclerAdapter extends RecyclerView.Adapter<SimpleRecyclerAdapter.ViewHolder> {

    private List<String> mItemsList;
    private PageFragment.OnListItemClickListener mListItemClickListener;

    public SimpleRecyclerAdapter(List<String> itemsList) {
        mItemsList = itemsList == null ? new ArrayList<String>() : itemsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Create a new view by inflating the row item xml.
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false);

        // Set the view to the ViewHolder
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final String itemTitle = mItemsList.get(position);
        holder.title.setText(itemTitle);
    }

    @Override
    public int getItemCount() {
        return mItemsList.size();
    }

    // Create the ViewHolder class to keep references to your views
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView title;

        /**
         * Constructor
         * @param v The container view which holds the elements from the row item xml
         */
        public ViewHolder(View v) {
            super(v);

            title = (TextView) v.findViewById(R.id.title);
            v.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            if (null != mListItemClickListener) {
                // Notify the active callbacks interface (the activity, if the
                // fragment is attached to one) that an item has been selected.
                mListItemClickListener.onListItemClick(mItemsList.get(getAdapterPosition()));
            }
        }
    }

    public void setOnItemTapListener(PageFragment.OnListItemClickListener itemClickListener) {
        mListItemClickListener = itemClickListener;
    }
}
