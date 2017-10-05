package com.puertomorelosapp.puertomorelosapp.Fragments.AboutUs;

import android.content.Context;

/**
 * Created by rudielavilaperaza on 6/13/17.
 */

public interface IAboutUs_Presenter {

    void setView(IAboutUs_View view);

    void onTwitterClick(Context context);

    void onFacebookClick(Context context);

    void onInstagramClick(Context context);

    void onContactClick(Context context);
}
