package com.puertomorelosapp.puertomorelosapp.Fragments.AboutUs;

import android.content.Context;

import com.puertomorelosapp.puertomorelosapp.Utils.PuertoMorelosApplication;

/**
 * Created by rudielavilaperaza on 6/13/17.
 */

public class AboutUs_Presenter implements IAboutUs_Presenter {

    IAboutUs_View view;

    public AboutUs_Presenter(Context context) {
        ((PuertoMorelosApplication) context).getAppComponent().inject(this);

    }

    @Override
    public void setView(IAboutUs_View view) {
        this.view = view;

    }

    @Override
    public void onTwitterClick() {
        view.showTwitter();
    }

    @Override
    public void onFacebookClick() {
        view.showFacebook();
    }

    @Override
    public void onInstagramClick() {
        view.showInstagram();
    }

    @Override
    public void onContactClick() {
        view.showContact();
    }
}
