package com.lizoan.testingrecipe.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lizoan.testingrecipe.R;
import com.lizoan.testingrecipe.listeners.OnTapAboutListener;

import java.util.ArrayList;

/**
 * Created by Ivan on 8/13/2017.
 */

public class AboutAdapter extends RecyclerView.Adapter<AboutAdapter.ViewHolder> {

    // Create arraylist variables to store data.
    private final ArrayList<String> titles;
    private final ArrayList<String> summaries;
    private OnTapAboutListener onTapAboutListener;

    private Context mContext;

    public AboutAdapter(Context context) {
        this.titles = new ArrayList<String>();
        this.summaries = new ArrayList<String>();

        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.about_adapter, null);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (onTapAboutListener != null)
                    onTapAboutListener.onTapView(position);

            }
        });

        holder.txtTitle.setText(titles.get(position));
        holder.txtSummary.setText(titles.get(position));

    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    // Update data to item list.
    public void updateList(
            ArrayList<String> titles,
            ArrayList<String> summaries) {
        this.titles.clear();
        this.titles.addAll(titles);

        this.summaries.clear();
        this.summaries.addAll(summaries);

        this.notifyDataSetChanged();
    }

    public void setOnTapAboutListener(OnTapAboutListener onTapAboutListener) {
        this.onTapAboutListener = onTapAboutListener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle, txtSummary;

        public ViewHolder(View v) {
            super(v);
            // Connect views object and views id on xml.
            txtTitle = v.findViewById(R.id.txtTitle);
            txtSummary = v.findViewById(R.id.txtSubTitle);
        }
    }
}
