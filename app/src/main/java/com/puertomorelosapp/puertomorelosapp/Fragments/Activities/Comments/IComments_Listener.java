package com.puertomorelosapp.puertomorelosapp.Fragments.Activities.Comments;

import com.puertomorelosapp.puertomorelosapp.Models.Response.Comments;

/**
 * Created by rudielavilaperaza on 9/12/17.
 */

public interface IComments_Listener {

    void onEditComment(Comments comment);

    void onDelete(Comments comment);
}
