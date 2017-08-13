package com.lizoan.testingrecipe.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.lizoan.testingrecipe.R;

import java.util.List;

/**
 * Created by Ivan on 8/13/2017.
 */

public class DetailPagerAdapter extends FragmentPagerAdapter {

    // Create variables and list for titles and pagers.
    private final String[] pagerTitles;
    private final List<Fragment> pagerFragments;

    public DetailPagerAdapter(FragmentManager fm, Context context, List<Fragment> pagerFragments) {
        super(fm);
        Resources res = context.getResources();
        pagerTitles = res.getStringArray(R.array.detail_pagers);

        this.pagerFragments = pagerFragments;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return pagerTitles[position];
    }

    @Override
    public int getCount() {
        return pagerTitles.length;
    }

    @Override
    public Fragment getItem(int position) {
        return this.pagerFragments.get(position);
    }

}
