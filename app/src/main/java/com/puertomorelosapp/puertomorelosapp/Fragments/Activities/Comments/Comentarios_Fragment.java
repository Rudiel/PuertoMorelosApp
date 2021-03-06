package com.puertomorelosapp.puertomorelosapp.Fragments.Activities.Comments;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.google.firebase.auth.FirebaseAuth;
import com.puertomorelosapp.puertomorelosapp.Adpaters.Activities_Comments_Adapter;
import com.puertomorelosapp.puertomorelosapp.Creators.ConfirmDialog_Creator;
import com.puertomorelosapp.puertomorelosapp.Creators.IConfirmComment_Creator;
import com.puertomorelosapp.puertomorelosapp.Models.Request.NewComment;
import com.puertomorelosapp.puertomorelosapp.Models.Response.Comments;
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

public class Comentarios_Fragment extends Fragment implements IComentarios_View, IComments_Listener {

    @Inject
    IComentarios_Presenter presenter;

    @Bind(R.id.rvActivitiesComments)
    RecyclerView rvActivitiesComments;

    @Bind(R.id.pbActivitiesComments)
    ProgressBar pbActivitiesComments;

    private Activities_Comments_Adapter adapter;

    private List<Comments> commentsList;

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

        commentsList = new ArrayList<>();

        if (!FirebaseAuth.getInstance().getCurrentUser().isAnonymous())
            presenter.getComentarios(getActivity());
    }

    @Override
    public void showComentarios(List<Comments> commentsList) {

        this.commentsList = commentsList;

        Collections.sort(this.commentsList, new Comparator<Comments>() {
            @Override
            public int compare(Comments o1, Comments o2) {
                return o1.getFecha().compareTo(o2.getFecha());
            }
        });

        Collections.reverse(this.commentsList);

        adapter = new Activities_Comments_Adapter(getActivity(), this.commentsList, this);

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

        new ConfirmDialog_Creator().showWriteComment(getActivity(), null, null, new IConfirmComment_Creator() {
            @Override
            public void onCancel(Dialog dialog) {
                dialog.dismiss();
            }

            @Override
            public void onAccept(Dialog dialog, NewComment comment) {

            }

            @Override
            public void onEdit(Dialog dialog, Comments comment) {
                presenter.editComment(getActivity(), comment);
                dialog.dismiss();
            }
        }, comment);

    }

    @Override
    public void onDelete(Comments comment) {

        presenter.deleteComment(getActivity(), comment);

    }
}
