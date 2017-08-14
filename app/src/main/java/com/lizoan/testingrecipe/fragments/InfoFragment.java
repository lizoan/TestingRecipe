package com.lizoan.testingrecipe.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.lizoan.testingrecipe.R;
import com.lizoan.testingrecipe.utils.Utils;

/**
 * Created by Ivan on 8/14/2017.
 */

public class InfoFragment extends Fragment {

    // Create objects of views.
    private WebView webInfo;

    // Create variable to store data.
    private String info;

    // Format webview content.
    private String htmlFormat = "<body bgcolor=\"#FBFBFB\">" +
            "<font color=\"#212121\">"+ Utils.ARG_TAG_CONTENT+ "</font>" +
            "</body>";

    public static InfoFragment newInstance(String info) {
        InfoFragment fragment = new InfoFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Utils.ARG_INFO, info);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Store data that pass from activity to info.
        info = getArguments().getString(Utils.ARG_INFO);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.info_fragment,container,false);
        // Connect view objects and view id on xml.
        webInfo = rootView.findViewById(R.id.webInfo);

        webInfo.getSettings().setDefaultFontSize(14);

        // Load value to webview.
        webInfo.loadDataWithBaseURL(null, htmlFormat.replace(Utils.ARG_TAG_CONTENT, info), "text/html", "utf-8", null);

        return rootView;
    }

}
