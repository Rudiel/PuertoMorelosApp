package com.puertomorelosapp.puertomorelosapp.Main;

import com.puertomorelosapp.puertomorelosapp.Models.Categorie;

import java.util.List;

/**
 * Created by rudielavilaperaza on 6/8/17.
 */

public interface IMain_View {

    void showLoading();

    void hideLoading();

    void logoutSesion();

    void setComments(int comments);

    void setLikes(int likes);

    void showAd(String URL);
}
