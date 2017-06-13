package com.puertomorelosapp.puertomorelosapp.Models;

import android.support.v4.app.Fragment;

/**
 * Created by rudielavilaperaza on 6/13/17.
 */

public class Activity {

    private Fragment fragment;
    private String title;

    public Fragment getFragment() {
        return fragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
