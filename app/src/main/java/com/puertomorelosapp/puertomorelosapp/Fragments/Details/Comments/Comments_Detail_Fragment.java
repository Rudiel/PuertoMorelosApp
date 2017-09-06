package com.puertomorelosapp.puertomorelosapp.Fragments.Details.Comments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.puertomorelosapp.puertomorelosapp.Adpaters.Comments_Adapter;
import com.puertomorelosapp.puertomorelosapp.Creators.ConfirmDialog_Creator;
import com.puertomorelosapp.puertomorelosapp.Creators.IConfirmComment_Creator;
import com.puertomorelosapp.puertomorelosapp.Creators.IConfirmDialog_Creator;
import com.puertomorelosapp.puertomorelosapp.Main.Main_Activity;
import com.puertomorelosapp.puertomorelosapp.Models.Request.NewComment;
import com.puertomorelosapp.puertomorelosapp.Models.Request.RoutesComments;
import com.puertomorelosapp.puertomorelosapp.Models.Response.Comments;
import com.puertomorelosapp.puertomorelosapp.R;
import com.puertomorelosapp.puertomorelosapp.Utils.PuertoMorelosApplication;
import com.puertomorelosapp.puertomorelosapp.Utils.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
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

    @Bind(R.id.btWriteComment)
    Button btWriteComment;

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    Main_Activity activity;

    private DatabaseReference commentsReference;

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

        activity = ((Main_Activity) getActivity());

        commentsReference = FirebaseDatabase.getInstance().getReference();

        Glide.with(getActivity()).load(R.drawable.background_comments).centerCrop().dontTransform().into(ivCommentsDetailBackground);

        tvDetailCommentsTitle.setTypeface(Utils.getbukharisLetter(getActivity()));

        rvCommentsDetail.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getActivity());

        rvCommentsDetail.setLayoutManager(mLayoutManager);

        presenter.setDatabaseReference(commentsReference);

        btWriteComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showWriteComment();
            }
        });

        commentsReference.child("PuertoMorelos/SocialAPP/Comments").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Log.d("CHANGED", "VALUE WAS CHANGED");

                presenter.getComments(activity.subCategory.getId());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


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

        Collections.sort(commentList, new Comparator<Comments>() {
            @Override
            public int compare(Comments o1, Comments o2) {
                if (o1.getTimeStamp() > o2.getTimeStamp())
                    return 1;
                else
                    return 0;
            }
        });

        mAdapter = new Comments_Adapter(commentList, getActivity());

        rvCommentsDetail.setAdapter(mAdapter);

    }


    private void showWriteComment() {
        new ConfirmDialog_Creator().showWriteComment(getActivity(),
                "New Comment", null, new IConfirmComment_Creator() {
                    @Override
                    public void onCancel(Dialog dialog) {
                        dialog.dismiss();
                    }

                    @Override
                    public void onAccept(Dialog dialog, NewComment comment) {

                        dialog.dismiss();

                        comment.setActivo(1);

                        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                        comment.setFecha(input.format(new Date()));
                        comment.setImageURL(Utils.getUserImage(getActivity()));
                        comment.setNombreEntidad(activity.subCategory.getNombre());
                        comment.setSenderDisplayName(Utils.getUserName(getActivity()));
                        comment.setSenderId(Utils.getProvider(getActivity()));
                        comment.setText(comment.getText());
                        comment.setTimeStamp((double) System.currentTimeMillis());
                        comment.setItemKey(activity.subCategory.getId());

                        String url = "";

                        if (activity.category.getCategoria() == null) {
                            comment.setCategoria(activity.mainCategory);
                            url = Utils.COMMENTS_URL + activity.category.getName() + "/" + activity.subCategory.getId();
                        } else {
                            comment.setCategoria(activity.category.getCategoria());
                            url = Utils.COMMENTS_URL + activity.category.getCategoria() + "/" + activity.category.getName() + "/" + activity.subCategory.getId();
                        }

                        RoutesComments routesComments = new RoutesComments();
                        routesComments.setUrl1(url);
                        routesComments.setUrl2(Utils.COMMENTS_SOCIAL_URL + "/" + Utils.getProvider(getActivity()));
                        routesComments.setUrl3(Utils.COMMENTS_COUNT + Utils.getProvider(getActivity()) + "/" + "UniversalComments");

                        presenter.setNewComment(comment, routesComments);
                    }
                });
    }


}
