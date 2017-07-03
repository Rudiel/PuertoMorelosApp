package com.puertomorelosapp.puertomorelosapp.Fragments.Details;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.transition.TransitionInflater;
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

    @Bind(R.id.tvDetailHorario)
    TextView tvHorario;

    @Bind(R.id.tvDetailFechaDia)
    TextView tvFechaDia;

    @Bind(R.id.tvDetailTelefono)
    TextView tvTelefono;

    @Bind(R.id.tvDetailDireccion)
    TextView tvDireccion;

    @Bind(R.id.tvDetailTitle2)
    TextView tvTitle2;

    @Bind(R.id.tvDetailDescription2)
    TextView tvDescripcion2;

    @Bind(R.id.tvDetailTitle3)
    TextView tvTitle3;

    @Bind(R.id.tvDetailDescription3)
    TextView tvDescripcion3;

    @Bind(R.id.tvDetailAcces)
    TextView tvAcceso;


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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            setSharedElementEnterTransition(TransitionInflater.from(getContext()).inflateTransition(android.R.transition.move));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            setSharedElementReturnTransition(TransitionInflater.from(getContext()).inflateTransition(android.R.transition.move));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ((Main_Activity) getActivity()).toolbar.setVisibility(View.GONE);

        subCategory = ((Main_Activity) getActivity()).subCategory;

        //Seteamos los datos
        /*if (subCategory.getImageBackgroundContent() != null)
            Glide.with(getActivity()).load(subCategory.getImageBackgroundContent()).into(ivDetail);*/
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ivDetail.setTransitionName(subCategory.getNombre());
        }

        if (subCategory.getImageBackgroundContent() != null)
            Glide.with(getActivity()).load(subCategory.getImageBackgroundContent()).into(ivDetail);

        tvLikes.setText(String.valueOf(subCategory.getLikes()));

        tvComments.setText(String.valueOf(subCategory.getComments()));

        tvDescripcion.setText(subCategory.getDescripcion());

        tvNombre.setText(subCategory.getNombre());

        tvTitle.setText(subCategory.getTitulo());

        if (subCategory.getFechadias() != null) {
            tvFechaDia.setVisibility(View.VISIBLE);
            tvFechaDia.setText(subCategory.getFechadias());
        } else
            tvFechaDia.setVisibility(View.GONE);

        if (subCategory.getHorainicio() != null & subCategory.getHorafin() != null) {
            tvHorario.setVisibility(View.VISIBLE);
            tvHorario.setText(subCategory.getHorainicio() + " - " + subCategory.getHorafin());
        } else
            tvHorario.setVisibility(View.GONE);

        if (subCategory.getTelefono() != null) {
            tvTelefono.setVisibility(View.VISIBLE);
            tvTelefono.setText(subCategory.getTelefono());
        } else {
            tvTelefono.setVisibility(View.GONE);
        }

        if (subCategory.getDireccion() != null) {
            tvDireccion.setText(subCategory.getDireccion());
            tvDireccion.setVisibility(View.VISIBLE);
        } else
            tvDireccion.setVisibility(View.GONE);

        if (subCategory.getDescripcion2() != null) {
            tvDescripcion2.setVisibility(View.VISIBLE);
            tvDescripcion2.setText(subCategory.getDescripcion2());
        } else
            tvDescripcion2.setVisibility(View.GONE);

        if (subCategory.getTitulo2() != null) {
            tvTitle2.setVisibility(View.VISIBLE);
            tvTitle2.setText(subCategory.getTitulo2());
        } else
            tvTitle2.setVisibility(View.GONE);

        if (subCategory.getDescripcion3() != null) {
            tvDescripcion3.setVisibility(View.VISIBLE);
            tvDescripcion3.setText(subCategory.getDescripcion3());
        } else
            tvDescripcion3.setVisibility(View.GONE);

        if (subCategory.getTitulo3() != null) {
            tvTitle3.setVisibility(View.VISIBLE);
            tvTitle3.setText(subCategory.getTitulo3());
        } else
            tvTitle3.setVisibility(View.GONE);

        if (subCategory.getAcceso() != null) {

        }


    }
}
