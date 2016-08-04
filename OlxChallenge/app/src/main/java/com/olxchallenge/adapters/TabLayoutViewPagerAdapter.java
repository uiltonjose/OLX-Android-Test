package com.olxchallenge.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.olxchallenge.fragments.ListAdsFragment;
import com.olxchallenge.fragments.MapFragment;

public class TabLayoutViewPagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public TabLayoutViewPagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new MapFragment();
            case 1:
                return new ListAdsFragment();
            default:
                return new MapFragment();
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}