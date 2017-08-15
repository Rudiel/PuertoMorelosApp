package com.puertomorelosapp.puertomorelosapp.Fragments.Details.Photos;

import android.content.Context;

import com.puertomorelosapp.puertomorelosapp.Utils.PuertoMorelosApplication;

/**
 * Created by rudielavilaperaza on 8/15/17.
 */

public class Photos_Detail_Presenter implements IPhotos_Presenter {

    IPhotos_View view;

    public Photos_Detail_Presenter(Context context) {

        ((PuertoMorelosApplication) context).getAppComponent().inject(this);

    }

    @Override
    public void getSelfies() {

        view.showLoading();

        //Obtenemos las selfies

        view.hideLoading();
    }

    @Override
    public void getGallery() {

        view.showLoading();

        //Obtenemos la galeria

        view.hideLoading();

    }

    @Override
    public void setView(IPhotos_View view) {
        this.view = view;
    }
}
