package com.puertomorelosapp.puertomorelosapp.Fragments.Activities.Selfies;

import android.content.Context;

import com.puertomorelosapp.puertomorelosapp.Models.Request.Selfie;

/**
 * Created by rudielavilaperaza on 9/22/17.
 */

public interface ISelfies_Presenter {

    void getSelfies(Context context);

    void setView(ISelfies_View view);

    void deleteSelfie(Selfie selfie, Context context);

}
