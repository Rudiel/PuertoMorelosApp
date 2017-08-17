package com.puertomorelosapp.puertomorelosapp.Fragments.Details.Photos;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.puertomorelosapp.puertomorelosapp.Adpaters.Gallery_Adapter;
import com.puertomorelosapp.puertomorelosapp.Adpaters.Selfies_Adapter;
import com.puertomorelosapp.puertomorelosapp.Creators.Gallery_Dialog_Creator;
import com.puertomorelosapp.puertomorelosapp.Main.Main_Activity;
import com.puertomorelosapp.puertomorelosapp.Models.Request.Gallery;
import com.puertomorelosapp.puertomorelosapp.Models.Request.Selfie;
import com.puertomorelosapp.puertomorelosapp.R;
import com.puertomorelosapp.puertomorelosapp.Utils.PuertoMorelosApplication;
import com.puertomorelosapp.puertomorelosapp.Utils.Utils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by rudielavilaperaza on 8/15/17.
 */

public class Photos_Detail_Fragment extends Fragment implements IPhotos_View, IGalleryClickListener, ISelfieClickListener {

    @Bind(R.id.rvSelfies)
    RecyclerView rvSelfies;

    @Bind(R.id.rvGallery)
    RecyclerView rvGallery;

    @Bind(R.id.tvPhotosGalleryTitle)
    TextView tvPhotosGalleryTitle;

    @Bind(R.id.tvPhotosSelfiesTitle)
    TextView tvPhotosSelfiesTitle;

    @Bind(R.id.llPhotosGallery)
    RelativeLayout llPhotosGallery;

    @Bind(R.id.llPhotosSelfies)
    RelativeLayout llPhotosSelfies;

    @Bind(R.id.pbGallery)
    ProgressBar pbGallery;

    @Bind(R.id.pbSelfies)
    ProgressBar pbSelfies;

    @Inject
    IPhotos_Presenter presenter;

    private Main_Activity activity;

    int numberOfColumns = 3;

    private List<Gallery> galleryList;

    private List<Selfie> selfieList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_pictures, container, false);

        ((PuertoMorelosApplication) getActivity().getApplication()).getAppComponent().inject(this);

        ButterKnife.bind(this, view);

        presenter.setView(this);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        activity = ((Main_Activity) getActivity());

        tvPhotosGalleryTitle.setTypeface(Utils.getbukharisLetter(getActivity()));
        tvPhotosSelfiesTitle.setTypeface(Utils.getbukharisLetter(getActivity()));

        galleryList = new ArrayList<>();

        selfieList = new ArrayList<>();

        presenter.getGallery(activity.category, activity.subCategory);

        presenter.getSelfies(activity.category, activity.subCategory);
    }


    @Override
    public void showLoadingSelfie() {
        pbSelfies.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingSelfie() {
        pbSelfies.setVisibility(View.GONE);
    }

    @Override
    public void showLoadingGallery() {
        pbGallery.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingGallery() {
        pbGallery.setVisibility(View.GONE);
    }

    @Override
    public void showGallery(List<Gallery> galleryList) {

        if (galleryList.size() > 0)
            llPhotosGallery.setVisibility(View.VISIBLE);

        this.galleryList = galleryList;

        rvGallery.setLayoutManager(new GridLayoutManager(getActivity(), numberOfColumns));
        rvGallery.setAdapter(new Gallery_Adapter(galleryList, getActivity(), Photos_Detail_Fragment.this));
    }

    @Override
    public void showPhotos(List<Selfie> selfieList) {

        this.selfieList = selfieList;

        rvSelfies.setLayoutManager(new GridLayoutManager(getActivity(), numberOfColumns));
        rvSelfies.setAdapter(new Selfies_Adapter(selfieList, getActivity(), Photos_Detail_Fragment.this));


    }

    @Override
    public void onGalleryClick(int position) {
        new Gallery_Dialog_Creator().showGallery(getActivity(), this.galleryList, position);
    }

    @Override
    public void onSlefieClick(int position) {

    }
}
