package com.puertomorelosapp.puertomorelosapp.Fragments.Activities.Likes;

import android.content.Context;

import com.puertomorelosapp.puertomorelosapp.Models.Request.Like;

/**
 * Created by rudielavilaperaza on 9/11/17.
 */

public interface IMegusta_Presenter {

    void setView(IMegusta_View view);

    void getLikesList(Context context);

    void deleteLike(Like like, Context context);
}
