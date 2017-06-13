package com.puertomorelosapp.puertomorelosapp.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.puertomorelosapp.puertomorelosapp.Adpaters.Activities_Adapter;
import com.puertomorelosapp.puertomorelosapp.Fragments.Activities.Comentarios_Fragment;
import com.puertomorelosapp.puertomorelosapp.Fragments.Activities.Megusta_Fragment;
import com.puertomorelosapp.puertomorelosapp.Fragments.Activities.Selfies_Fragment;
import com.puertomorelosapp.puertomorelosapp.Models.Activity;
import com.puertomorelosapp.puertomorelosapp.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by rudielavilaperaza on 6/12/17.
 */


public class Activity_Fragment extends Fragment {

    @Bind(R.id.tlActivities)
    TabLayout tlActivities;

    @Bind(R.id.vpActivities)
    ViewPager viewPager;

    private List<Activity> activities;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_activity, container, false);

        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        activities = new ArrayList<>();

        setFragmentsDefault();

        tlActivities.setupWithViewPager(viewPager);


    }

    private void setUpViewPager() {
        Activities_Adapter adapter = new Activities_Adapter(getFragmentManager(), activities);
        viewPager.setAdapter(adapter);

    }


    private void setFragmentsDefault() {

        Activity megusta = new Activity();
        megusta.setFragment(new Megusta_Fragment());
        megusta.setTitle("Me Gusta");

        activities.add(megusta);

        Activity comentarios = new Activity();
        comentarios.setFragment(new Comentarios_Fragment());
        comentarios.setTitle("Comentarios");

        activities.add(comentarios);

        Activity selfies = new Activity();
        selfies.setTitle("Selfies");
        selfies.setFragment(new Selfies_Fragment());

        activities.add(selfies);

        setUpViewPager();

    }
}
