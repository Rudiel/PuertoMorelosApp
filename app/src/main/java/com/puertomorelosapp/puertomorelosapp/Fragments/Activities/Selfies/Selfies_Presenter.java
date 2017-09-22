package com.puertomorelosapp.puertomorelosapp.Fragments.Activities.Selfies;

import android.content.Context;

import com.puertomorelosapp.puertomorelosapp.Utils.PuertoMorelosApplication;

/**
 * Created by rudielavilaperaza on 9/14/17.
 */

public class Selfies_Presenter implements ISelfies_Presenter {

    public Selfies_Presenter(Context context) {

        ((PuertoMorelosApplication) context).getAppComponent().inject(this);

    }

    @Override
    public void getSelfies() {

    }

    @Override
    public void setView(ISelfies_View view) {

    }
}
