package com.puertomorelosapp.puertomorelosapp.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.puertomorelosapp.puertomorelosapp.Adpaters.Activities_Adapter;
import com.puertomorelosapp.puertomorelosapp.Creators.Tuturial_Activity_Creator;
import com.puertomorelosapp.puertomorelosapp.Fragments.Activities.Comments.Comentarios_Fragment;
import com.puertomorelosapp.puertomorelosapp.Fragments.Activities.Likes.Megusta_Fragment;
import com.puertomorelosapp.puertomorelosapp.Fragments.Activities.Selfies.Selfies_Fragment;
import com.puertomorelosapp.puertomorelosapp.Main.Main_Activity;
import com.puertomorelosapp.puertomorelosapp.Models.Activity;
import com.puertomorelosapp.puertomorelosapp.Models.Tutorial;
import com.puertomorelosapp.puertomorelosapp.R;
import com.puertomorelosapp.puertomorelosapp.Utils.NoSwipeableViewPager;

import java.io.UnsupportedEncodingException;
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
    NoSwipeableViewPager viewPager;

    private List<Activity> activities;

    private Toolbar toolbar;

    private ImageView ivInfo;

    private List<Tutorial> tutorialList;

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

        //((Main_Activity) getActivity()).ivMap.setVisibility(View.GONE);

        tutorialList = new ArrayList<>();

        toolbar = ((Main_Activity) getActivity()).toolbar;

        ivInfo = (ImageView) toolbar.findViewById(R.id.ivMap);

        ivInfo.setVisibility(View.VISIBLE);

        ivInfo.setImageDrawable(getResources().getDrawable(R.drawable.ic_info_outline_white_36dp));

        ivInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Tuturial_Activity_Creator().showTutorial(getActivity(), tutorialList);
            }
        });


        tlActivities.setupWithViewPager(viewPager);

        /*viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });*/

        createTutorialList();


    }

    private void setUpViewPager() {
        Activities_Adapter adapter = new Activities_Adapter(getChildFragmentManager(), activities);
        viewPager.setAdapter(adapter);
    }


    private void setFragmentsDefault() {

        Activity megusta = new Activity();
        megusta.setFragment(new Megusta_Fragment());
        megusta.setTitle(getString(R.string.tab_likes));

        activities.add(megusta);

        Activity comentarios = new Activity();
        comentarios.setFragment(new Comentarios_Fragment());
        comentarios.setTitle(getString(R.string.tab_comments));

        activities.add(comentarios);

        Activity selfies = new Activity();
        selfies.setTitle(getString(R.string.tab_selfies));
        selfies.setFragment(new Selfies_Fragment());

        activities.add(selfies);

        setUpViewPager();

    }

    private void createTutorialList() {

        Tutorial tutorial1 = new Tutorial();
        tutorial1.setTitle(getString(R.string.tutorial_delete_title));
        tutorial1.setSubtitle(getString(R.string.tutorial_delete_subtitle));
        tutorial1.setImage(getActivity().getResources().getDrawable(R.drawable.ic_gesture_swipe_left_white_48dp));

        tutorialList.add(tutorial1);

        //String string = "U+1F602";
        String string = "\uD83D\uDE23";

        try {
            // Convert from Unicode to UTF-8
            byte[] utf8 = string.getBytes("UTF-8");

            // Convert from UTF-8 to Unicode
            string = new String(utf8, "UTF-8");
        } catch (UnsupportedEncodingException e) {
        }

        Tutorial tutorial2 = new Tutorial();
        tutorial2.setTitle(getString(R.string.tutorial_selfie_title) + " " + string);
        tutorial2.setSubtitle(getString(R.string.tutorial_selfie_subtitle));
        tutorial2.setImage(getActivity().getResources().getDrawable(R.drawable.ic_delete_white_48dp));

        tutorialList.add(tutorial2);

        //U+1F623


    }
}
