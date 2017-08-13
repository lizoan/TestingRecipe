package com.lizoan.testingrecipe.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lizoan.testingrecipe.R;
import com.lizoan.testingrecipe.listeners.OnTapListener;
import com.makeramen.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Ivan on 8/13/2017.
 */

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.ViewHolder> {

    // Create arraylist variables to store data.
    private final ArrayList<String> recipeIds;
    private final ArrayList<String> recipeNames;
    private final ArrayList<String> cookTimes;
    private final ArrayList<String> servings;
    private final ArrayList<String> images;
    private OnTapListener onTapListener;

    private Context mContext;
    private String cookTime, minutes, serveFor, persons;

    public RecipesAdapter (Context context){

        this.recipeIds = new ArrayList<String>();
        this.recipeNames = new ArrayList<String>();
        this.cookTimes = new ArrayList<String>();
        this.servings = new ArrayList<String>();
        this.images = new ArrayList<String>();

        mContext = context;

        cookTime = mContext.getResources().getString(R.string.cook_time);
        minutes = mContext.getResources().getString(R.string.minutes);
        serveFor = mContext.getResources().getString(R.string.serve_for);
        persons = mContext.getResources().getString(R.string.persons);

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recipes_adapter, null);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (onTapListener != null)
                    onTapListener.onTapView(recipeIds.get(position), "");

            }
        });

        // Set data to textview.
        holder.txtRecipeName.setText(recipeNames.get(position));
        holder.txtTime.setText(cookTime+" "+cookTimes.get(position)+" "+minutes+", "+
                serveFor+" "+servings.get(position)+" "+persons);

        int image = mContext.getResources().getIdentifier(images.get(position), "drawable", mContext.getPackageName());
        Picasso.with(mContext)
                .load(image)
                .into(holder.imgRecipe);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView txtRecipeName, txtTime;
        RoundedImageView imgRecipe;

        public ViewHolder(View v)
        {
            super(v);
            // Connect views object and views id on xml.
            txtRecipeName = v.findViewById(R.id.txtTitle);
            txtTime = v.findViewById(R.id.txtSubTitle);
            imgRecipe = v.findViewById(R.id.imgThumbnail);
        }
    }
}
