package com.puertomorelosapp.puertomorelosapp.Fragments.Details.Comments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.puertomorelosapp.puertomorelosapp.Main.Main_Activity;
import com.puertomorelosapp.puertomorelosapp.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by rudielavilaperaza on 7/7/17.
 */

public class Comments_Detail_Fragment extends Fragment {

    @Bind(R.id.ivCommentsDetailBackground)
    ImageView ivCommentsDetailBackground;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_comments_detail_fragment, container, false);

        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Glide.with(getActivity()).load(R.drawable.background_comments).centerCrop().into(ivCommentsDetailBackground);

    }


}
