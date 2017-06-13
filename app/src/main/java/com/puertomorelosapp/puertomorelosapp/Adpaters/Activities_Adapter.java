package com.puertomorelosapp.puertomorelosapp.Adpaters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.puertomorelosapp.puertomorelosapp.Models.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rudielavilaperaza on 6/13/17.
 */

public class Activities_Adapter extends FragmentPagerAdapter {

    private List<Activity> activities = new ArrayList<>();


    public Activities_Adapter(FragmentManager fm, List<Activity> activities) {
        super(fm);
        this.activities = activities;
    }

    @Override
    public Fragment getItem(int position) {
        return activities.get(position).getFragment();
    }

    @Override
    public int getCount() {
        return activities.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return activities.get(position).getTitle();

    }


}
