
package edu.vandy.mooc.aad_3_assg_2.framework.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import edu.vandy.mooc.aad_3_assg_2.framework.Interfaces.RecyclerViewClickListener;
import edu.vandy.mooc.aad_3_assg_2.R;
import edu.vandy.mooc.aad_3_assg_2.framework.common.logger.Log;
import edu.vandy.mooc.aad_3_assg_2.framework.orm.Entry;

/**
 * Provide views to RecyclerView with data from mDataSet.
 */
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    private static final String TAG = "CustomAdapter";

    private Entry[] mDataSet;

    /**
     * Provide a reference to the type of views that you are using (custom ViewHolder)
     */
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private final TextView title;
        private final TextView author;
        private final TextView published;


        /**
         * Constructor
         * @param v The view being held.
         */

        public ViewHolder(View v) {
            super(v);
            // Define click listener for the ViewHolder's View.
            v.setOnClickListener(this);

            title = (TextView) v.findViewById(R.id.entry_row_title);
            author = (TextView) v.findViewById(R.id.entry_row_author);
            published = (TextView) v.findViewById(R.id.entry_row_published);
        }

        public TextView getTitle() {
            return title;
        }

        public TextView getAuthor() {
            return author;
        }

        public TextView getPublished() {
            return published;
        }

        /**
         * Called when a view has been clicked.
         *
         * @param v The view that was clicked.
         */
        @Override
        public void onClick(View v) {
            itemListener.recyclerViewListClicked(v, this.getLayoutPosition());
        }
    }

    private static RecyclerViewClickListener itemListener;

    public CustomAdapter(RecyclerViewClickListener listener, Entry[] dataSet) {
        itemListener = listener;
        mDataSet = dataSet;
    }

    public CustomAdapter(RecyclerViewClickListener listener) {
        itemListener = listener;
    }

    public void setData(List<Entry> entries) {
        mDataSet = entries.toArray(new Entry[entries.size()]);
    }

    public void setData(Entry[] entries) {
        mDataSet = entries;
    }

    public Entry[] getData() {
        return mDataSet;
    }

    // BEGIN_INCLUDE(recyclerViewOnCreateViewHolder)
    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view.
        android.util.Log.d(TAG, "New ViewHolder Created.");
        android.util.Log.d(TAG, "Log info: " + Log.getLogNode());
        Log.e(TAG, "New ViewHolder Created.");
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.text_row_item, viewGroup, false);

        return new ViewHolder(v);
    }
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Log.d(TAG, "Element " + position + " set.");

        // Get element from your dataset at this position and replace the contents of the view
        // with that element
        viewHolder.getTitle().setText(mDataSet[position].getTITLE());
        viewHolder.getAuthor().setText(mDataSet[position].getAUTHOR());
        viewHolder.getPublished().setText(mDataSet[position].getPUBLISHED());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataSet != null ? mDataSet.length : 0;
    }

    public Entry getItem(int position) {
        return mDataSet[position];
    }
}

