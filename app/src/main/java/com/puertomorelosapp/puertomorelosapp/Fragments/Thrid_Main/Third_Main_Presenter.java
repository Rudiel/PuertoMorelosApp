package com.puertomorelosapp.puertomorelosapp.Fragments.Thrid_Main;

import android.content.Context;

import com.puertomorelosapp.puertomorelosapp.Utils.PuertoMorelosApplication;

/**
 * Created by rudielavilaperaza on 6/27/17.
 */

public class Third_Main_Presenter implements IThird_Main_Presenter {

    private IThird_View view;

    public Third_Main_Presenter(Context context) {

        ((PuertoMorelosApplication) context).getAppComponent().inject(this);
    }

    @Override
    public void setView(IThird_View view) {
        this.view = view;
    }

    @Override
    public void getThirdCategories() {

    }
}
