package com.puertomorelosapp.puertomorelosapp.Fragments.Activities.Comments;


import com.puertomorelosapp.puertomorelosapp.Models.Response.Comments;

import java.util.List;

/**
 * Created by rudielavilaperaza on 9/12/17.
 */

public interface IComentarios_View {

    void showComentarios(List<Comments> commentsList);

    void showLoading();

    void hideLoading();
}
