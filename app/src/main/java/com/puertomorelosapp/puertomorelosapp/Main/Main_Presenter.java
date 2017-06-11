package com.puertomorelosapp.puertomorelosapp.Main;

import android.content.Context;

import com.puertomorelosapp.puertomorelosapp.Utils.PuertoMorelosApplication;

/**
 * Created by rudielavilaperaza on 6/8/17.
 */

public class Main_Presenter implements IMain_Presenter {

    IMain_View view;

    public Main_Presenter(Context context) {
        ((PuertoMorelosApplication) context).getAppComponent().inject(this);

    }

    @Override
    public void setView(IMain_View view) {
        this.view = view;
    }

    @Override
    public void getCategories() {

    }
}
