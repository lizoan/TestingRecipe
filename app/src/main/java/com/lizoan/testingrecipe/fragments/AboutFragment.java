package com.lizoan.testingrecipe.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lizoan.testingrecipe.R;
import com.lizoan.testingrecipe.activity.AboutActivity;
import com.lizoan.testingrecipe.adapters.AboutAdapter;
import com.lizoan.testingrecipe.listeners.OnTapAboutListener;

import java.util.ArrayList;

/**
 * Created by Ivan on 8/14/2017.
 */

public class AboutFragment extends Fragment {

    // Create objects of views.
    private RecyclerView recyclerView;
    private AboutAdapter adapterAbout;
    private OnItemSelectedListener mCallback;

    // Create arraylist variables to store data.
    private ArrayList<String> titles = new ArrayList<String>();
    private ArrayList<String> summaries = new ArrayList<String>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.about_fragment, container, false);

        setRetainInstance(true);

        // Connect view objects and view id on xml.
        recyclerView = rootView.findViewById(R.id.recycler_view);

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        // Get data with asynctask.
        new syncGetData().execute();

        adapterAbout = new AboutAdapter(getActivity());

        adapterAbout.setOnTapAboutListener(new OnTapAboutListener() {
            @Override
            public void onTapView(int position) {
                mCallback.onItemSelected(position);
            }
        });

        return rootView;
    }

    // Get data from string resources.
    public void getDataFromResources() {
        for (int i = 0; i < AboutActivity.titles.length; i++) {
            titles.add(AboutActivity.titles[i]);
            summaries.add(AboutActivity.summaries[i]);
        }
    }

    // Create interface listener.
    public interface OnItemSelectedListener {
        void onItemSelected(int position);
    }

    public class syncGetData extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            getDataFromResources();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            adapterAbout.updateList(titles, summaries);
            recyclerView.setAdapter(adapterAbout);
        }
    }
}
