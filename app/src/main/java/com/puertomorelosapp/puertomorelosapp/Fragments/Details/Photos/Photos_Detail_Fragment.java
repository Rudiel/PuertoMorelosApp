package com.puertomorelosapp.puertomorelosapp.Fragments.Details.Photos;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.puertomorelosapp.puertomorelosapp.R;
import com.puertomorelosapp.puertomorelosapp.Utils.PuertoMorelosApplication;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by rudielavilaperaza on 8/15/17.
 */

public class Photos_Detail_Fragment extends Fragment implements IPhotos_View {

    @Bind(R.id.rvSelfies)
    RecyclerView rvSelfies;

    @Bind(R.id.rvGallery)
    RecyclerView rvGallery;

    @Bind(R.id.tvPhotosGalleryTitle)
    TextView tvPhotosGalleryTitle;

    @Bind(R.id.tvPhotosSelfiesTitle)
    TextView tvPhotosSelfiesTitle;

    @Bind(R.id.llPhotosGallery)
    LinearLayout llPhotosGallery;

    @Bind(R.id.llPhotosSelfies)
    LinearLayout llPhotosSelfies;

    @Inject
    IPhotos_Presenter presenter;

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

        presenter.getGallery();

        presenter.getSelfies();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showGallery() {

        llPhotosGallery.setVisibility(View.VISIBLE);

    }

    @Override
    public void showPhotos() {

    }
}
