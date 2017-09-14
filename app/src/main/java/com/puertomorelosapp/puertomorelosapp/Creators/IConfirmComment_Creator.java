package com.puertomorelosapp.puertomorelosapp.Creators;

import android.app.Dialog;

import com.puertomorelosapp.puertomorelosapp.Models.Request.NewComment;
import com.puertomorelosapp.puertomorelosapp.Models.Response.Comments;

/**
 * Created by rudielavilaperaza on 8/13/17.
 */

public interface IConfirmComment_Creator {

    void onCancel(Dialog dialog);

    void onAccept(Dialog dialog, NewComment comment);

    void onEdit(Dialog dialog, Comments comment);

}
