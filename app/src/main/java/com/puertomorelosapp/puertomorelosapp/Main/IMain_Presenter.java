package com.puertomorelosapp.puertomorelosapp.Main;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by rudielavilaperaza on 6/8/17.
 */

public interface IMain_Presenter {

    void setView(IMain_View view);

    void getCategories();

    void logout(FirebaseAuth auth);
}
