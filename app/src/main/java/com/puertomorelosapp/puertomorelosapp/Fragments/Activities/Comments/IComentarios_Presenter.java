package com.puertomorelosapp.puertomorelosapp.Fragments.Activities.Comments;

import android.content.Context;

import com.puertomorelosapp.puertomorelosapp.Models.Response.Comments;

/**
 * Created by rudielavilaperaza on 9/12/17.
 */

public interface IComentarios_Presenter {

    void getComentarios(Context context);

    void setView(IComentarios_View view);

    void deleteComment(Context context, Comments comment);
}
