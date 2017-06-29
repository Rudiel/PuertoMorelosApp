package com.puertomorelosapp.puertomorelosapp.Fragments.Details;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.puertomorelosapp.puertomorelosapp.Main.Main_Activity;
import com.puertomorelosapp.puertomorelosapp.Models.SubCategory;
import com.puertomorelosapp.puertomorelosapp.R;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by rudielavilaperaza on 6/29/17.
 */

public class Detail_Fragment extends Fragment {

    private static View view;

    @Inject
    IDetail_Presenter presenter;

    @Bind(R.id.ivDetail)
    ImageView ivDetail;

    @Bind(R.id.tvDetailLikes)
    TextView tvLikes;

    @Bind(R.id.tvDetailComments)
    TextView tvComments;

    @Bind(R.id.tvDetailNombre)
    TextView tvNombre;

    @Bind(R.id.tvDetailDescription)
    TextView tvDescripcion;

    @Bind(R.id.tvDetailTitle)
    TextView tvTitle;


    private SubCategory subCategory;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null)
                parent.removeView(view);
        }
        try {
            view = inflater.inflate(R.layout.layout_deatil, container, false);
        } catch (InflateException e) {
        /* map is already there, just return view as it is */
        }

        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ((Main_Activity) getActivity()).toolbar.setVisibility(View.GONE);

        subCategory = ((Main_Activity) getActivity()).subCategory;

        //Seteamos los datos
        if (subCategory.getImageBackgroundContent() != null)
            Glide.with(getActivity()).load(subCategory.getImageBackgroundContent()).into(ivDetail);

        tvLikes.setText(String.valueOf(subCategory.getLikes()));

        tvComments.setText(String.valueOf(subCategory.getComments()));

        tvDescripcion.setText(subCategory.getDescripcion());

        tvNombre.setText(subCategory.getNombre());

        tvTitle.setText(subCategory.getTitulo());

    }
}
