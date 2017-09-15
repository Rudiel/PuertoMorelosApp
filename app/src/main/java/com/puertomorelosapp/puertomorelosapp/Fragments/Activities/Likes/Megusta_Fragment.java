package com.puertomorelosapp.puertomorelosapp.Fragments.Activities.Likes;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.puertomorelosapp.puertomorelosapp.Adpaters.Activities_Likes_Adapter;
import com.puertomorelosapp.puertomorelosapp.Models.Request.Like;
import com.puertomorelosapp.puertomorelosapp.R;
import com.puertomorelosapp.puertomorelosapp.Utils.PuertoMorelosApplication;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by rudielavilaperaza on 6/13/17.
 */

public class Megusta_Fragment extends Fragment implements IMegusta_View, IDelete_Like_Listener {

    @Bind(R.id.rvActivitiesLikes)
    RecyclerView rvActivitiesLikes;

    @Inject
    IMegusta_Presenter presenter;

    private Activities_Likes_Adapter adapter;

    private List<Like> likes;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_activities_like, container, false);

        ((PuertoMorelosApplication) getActivity().getApplication()).getAppComponent().inject(this);

        ButterKnife.bind(this, view);

        presenter.setView(this);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        rvActivitiesLikes.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvActivitiesLikes.setHasFixedSize(false);

        likes = new ArrayList<>();

        presenter.getLikesList(getActivity());

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void setLikesList(List<Like> likes) {

        this.likes = likes;

       Collections.sort(this.likes, new Comparator<Like>() {
           @Override
           public int compare(Like o1, Like o2) {
               return (o1.getFecha().compareTo(o2.getFecha()));
           }
       });

        Collections.reverse(this.likes);

        adapter = new Activities_Likes_Adapter(getActivity(), this.likes, this);

        rvActivitiesLikes.setAdapter(adapter);

    }

    @Override
    public void refreshList() {

        this.likes.clear();

        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDeleteListener(Like like) {

        presenter.deleteLike(like, getActivity());
    }
}
