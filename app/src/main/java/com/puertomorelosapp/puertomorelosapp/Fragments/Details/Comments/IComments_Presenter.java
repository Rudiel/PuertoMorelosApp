package com.puertomorelosapp.puertomorelosapp.Fragments.Details.Comments;

import com.puertomorelosapp.puertomorelosapp.Models.Request.NewComment;

/**
 * Created by rudielavilaperaza on 8/12/17.
 */

public interface IComments_Presenter {

    void getComments(String id);

    void setView(IComments_View view);

    void setNewComment(NewComment comment);
}
