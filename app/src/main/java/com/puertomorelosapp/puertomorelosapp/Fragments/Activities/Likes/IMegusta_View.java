package com.puertomorelosapp.puertomorelosapp.Fragments.Activities.Likes;

import com.puertomorelosapp.puertomorelosapp.Models.Request.Like;

import java.util.List;

/**
 * Created by rudielavilaperaza on 9/11/17.
 */

public interface IMegusta_View {

    void hideLoading();

    void showLoading();

    void setLikesList(List<Like> likes);
}
