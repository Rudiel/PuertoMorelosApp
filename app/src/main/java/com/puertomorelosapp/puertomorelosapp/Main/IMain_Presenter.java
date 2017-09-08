package com.puertomorelosapp.puertomorelosapp.Main;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by rudielavilaperaza on 6/8/17.
 */

public interface IMain_Presenter {

    void setView(IMain_View view);

    void logout(FirebaseAuth auth);

    void getMenuComments(String id);

    void getMenuLikes(String id);

    void getAd();

}
