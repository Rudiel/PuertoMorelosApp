package com.puertomorelosapp.puertomorelosapp.Fragments.Activities.Comments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.puertomorelosapp.puertomorelosapp.Adpaters.Activities_Comments_Adapter;
import com.puertomorelosapp.puertomorelosapp.Models.Response.Comments;
import com.puertomorelosapp.puertomorelosapp.R;
import com.puertomorelosapp.puertomorelosapp.Utils.PuertoMorelosApplication;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by rudielavilaperaza on 6/13/17.
 */

public class Comentarios_Fragment extends Fragment implements IComentarios_View, IComments_Listener {

    @Inject
    IComentarios_Presenter presenter;

    @Bind(R.id.rvActivitiesComments)
    RecyclerView rvActivitiesComments;

    @Bind(R.id.pbActivitiesComments)
    ProgressBar pbActivitiesComments;

    private Activities_Comments_Adapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.layout_activities_comments, container, false);

        ((PuertoMorelosApplication) getActivity().getApplication()).getAppComponent().inject(this);

        presenter.setView(this);

        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        rvActivitiesComments.setLayoutManager(new LinearLayoutManager(getActivity()));

        rvActivitiesComments.setHasFixedSize(false);

        presenter.getComentarios(getActivity());
    }

    @Override
    public void showComentarios(List<Comments> commentsList) {

        adapter = new Activities_Comments_Adapter(getActivity(), commentsList, this);

        rvActivitiesComments.setAdapter(adapter);

    }

    @Override
    public void showLoading() {
        pbActivitiesComments.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        pbActivitiesComments.setVisibility(View.GONE);
    }

    @Override
    public void onEditComment(Comments comment) {

    }

    @Override
    public void onDelete(Comments comment) {

    }
}
