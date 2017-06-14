package com.puertomorelosapp.puertomorelosapp.Fragments.AboutUs;

/**
 * Created by rudielavilaperaza on 6/13/17.
 */

public interface IAboutUs_Presenter {

    void setView(IAboutUs_View view);

    void onTwitterClick();

    void onFacebookClick();

    void onInstagramClick();

    void onContactClick();
}
