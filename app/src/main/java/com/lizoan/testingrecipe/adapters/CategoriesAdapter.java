package com.lizoan.testingrecipe.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lizoan.testingrecipe.R;
import com.lizoan.testingrecipe.listeners.OnTapListener;

import java.util.ArrayList;

/**
 * Created by Ivan on 8/13/2017.
 */

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder> {

    // Create arraylist variables to store data.
    private final ArrayList<String> categoryIds;
    private final ArrayList<String> categoryNames;
    private OnTapListener onTapListener;

    private Context mContext;

    public CategoriesAdapter(Context context) {

        this.categoryIds = new ArrayList<String>();
        this.categoryNames = new ArrayList<String>();

        mContext = context;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.categories_adapter, null);

        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onTapListener != null)
                    onTapListener.onTapView(categoryIds.get(position), categoryNames.get(position));
            }
        });

        // Set data to textview.
        holder.txtCategory.setText(categoryNames.get(position));

    }

    @Override
    public int getItemCount() {
        return categoryIds.size();
    }

    // Update data to item list.
    public void updateList(ArrayList<String> categoryIds, ArrayList<String> categoryNames) {

        this.categoryIds.clear();
        this.categoryIds.addAll(categoryIds);

        this.categoryNames.clear();
        this.categoryNames.addAll(categoryNames);

        this.notifyDataSetChanged();
    }

    public void setOnTapListener(OnTapListener onTapListener) {
        this.onTapListener = onTapListener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtCategory;

        public ViewHolder(View v) {
            super(v);
            // Connect views object and views id on xml.
            txtCategory = v.findViewById(R.id.txtCategory);
        }
    }
}
