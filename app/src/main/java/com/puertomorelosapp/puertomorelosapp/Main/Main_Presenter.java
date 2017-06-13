package com.puertomorelosapp.puertomorelosapp.Main;

import android.content.Context;
import android.support.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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

    @Override
    public void logout(FirebaseAuth auth) {
        auth.signOut();

        view.logoutSesion();
    }
}
