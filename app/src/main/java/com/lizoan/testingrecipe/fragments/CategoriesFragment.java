package com.lizoan.testingrecipe.fragments;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gc.materialdesign.views.ProgressBarCircularIndeterminate;
import com.lizoan.testingrecipe.R;
import com.lizoan.testingrecipe.adapters.CategoriesAdapter;
import com.lizoan.testingrecipe.listeners.OnTapListener;
import com.lizoan.testingrecipe.utils.RecipesDBHelper;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Okena Ivan Katenta on this day of developemnt
 * Fuck whoever wants to know
 * :)
 */
public class CategoriesFragment extends Fragment {

    // Create objects of views.
    private RecyclerView recyclerView;
    private ProgressBarCircularIndeterminate prgLoading;

    // Create instance of database helper.
    private RecipesDBHelper dbhelper;

    // Create instance of adapter.
    private CategoriesAdapter adapterCategories;

    private OnCategorySelectedListener mCallback;

    private ArrayList<ArrayList<Object>> data;

    // Create arraylist variables to store data.
    private ArrayList<String> categoryIds = new ArrayList<String>();
    private ArrayList<String> categoryNames = new ArrayList<String>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_categories, container, false);

        // Connect view objects and view id on xml.
        recyclerView = rootView.findViewById(R.id.recycler_view);
        prgLoading = rootView.findViewById(R.id.prgLoading);

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        // Create object of database helpers.
        dbhelper = new RecipesDBHelper(getActivity());

        // Create database of recipes.
        try {
            dbhelper.createDataBase();
        }catch(IOException ioe){
            throw new Error("Unable to create database");
        }

        // Open recipes database.
        dbhelper.openDataBase();

        new syncGetData().execute();

        adapterCategories = new CategoriesAdapter(getActivity());


        adapterCategories.setOnTapListener(new OnTapListener() {
            @Override
            public void onTapView(String ID, String CategoryName) {
                mCallback.onCategorySelected(ID, CategoryName);
            }
        });

        return rootView;
    }

    // Get data from database and store to arraylist variables.
    public void getDataFromDatabase(){
        data = dbhelper.getAllCategoriesData();

        for(int i = 0; i< data.size(); i++){
            ArrayList<Object> row = data.get(i);

            categoryIds.add(row.get(0).toString());
            categoryNames.add(row.get(1).toString());
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception.
        try {
            mCallback = (OnCategorySelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnCategorySelectedListener");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        dbhelper.close();
    }

    // Create interface listener.
    public interface OnCategorySelectedListener{
        void onCategorySelected(String ID, String CategoryName);
    }

    public class syncGetData extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            prgLoading.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
            categoryIds.clear();
            categoryNames.clear();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            getDataFromDatabase();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            prgLoading.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            adapterCategories.updateList(categoryIds, categoryNames);

            recyclerView.setAdapter(adapterCategories);

        }
    }
}
