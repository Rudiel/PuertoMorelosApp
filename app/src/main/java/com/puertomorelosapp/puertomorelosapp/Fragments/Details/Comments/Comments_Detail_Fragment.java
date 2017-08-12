package com.puertomorelosapp.puertomorelosapp.Fragments.Details.Comments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.puertomorelosapp.puertomorelosapp.Adpaters.Comments_Adapter;
import com.puertomorelosapp.puertomorelosapp.Main.Main_Activity;
import com.puertomorelosapp.puertomorelosapp.Models.Response.Comments;
import com.puertomorelosapp.puertomorelosapp.R;
import com.puertomorelosapp.puertomorelosapp.Utils.PuertoMorelosApplication;
import com.puertomorelosapp.puertomorelosapp.Utils.Utils;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by rudielavilaperaza on 7/7/17.
 */

public class Comments_Detail_Fragment extends Fragment implements IComments_View {

    @Bind(R.id.ivCommentsDetailBackground)
    ImageView ivCommentsDetailBackground;

    @Bind(R.id.pbComments)
    ProgressBar pbComments;

    @Bind(R.id.rvCommentsDetail)
    RecyclerView rvCommentsDetail;

    @Bind(R.id.tvDetailCommentsTitle)
    TextView tvDetailCommentsTitle;

    @Inject
    IComments_Presenter presenter;

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_comments_detail_fragment, container, false);

        ((PuertoMorelosApplication) getActivity().getApplication()).getAppComponent().inject(this);

        ButterKnife.bind(this, view);

        presenter.setView(this);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Glide.with(getActivity()).load(R.drawable.background_comments).centerCrop().into(ivCommentsDetailBackground);

        tvDetailCommentsTitle.setTypeface(Utils.getbukharisLetter(getActivity()));

        rvCommentsDetail.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getActivity());

        rvCommentsDetail.setLayoutManager(mLayoutManager);

        presenter.getComments(((Main_Activity) getActivity()).subCategory.getId());

    }


    @Override
    public void showLoading() {
        pbComments.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        pbComments.setVisibility(View.GONE);
    }

    @Override
    public void setComments(List<Comments> commentList) {
        Log.d("Comments", "" + commentList.size());

        mAdapter = new Comments_Adapter(commentList, getActivity());

        rvCommentsDetail.setAdapter(mAdapter);
    }
}
