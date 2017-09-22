package com.puertomorelosapp.puertomorelosapp.Fragments.Activities.Selfies;

import com.puertomorelosapp.puertomorelosapp.Models.Request.Selfie;

import java.util.List;

/**
 * Created by rudielavilaperaza on 9/22/17.
 */

public interface ISelfies_View {

    void showLoading();

    void hideLoading();

    void setSelfies(List<Selfie> selfies);

}
