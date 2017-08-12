package com.puertomorelosapp.puertomorelosapp.Fragments.Details.Comments;

/**
 * Created by rudielavilaperaza on 8/12/17.
 */

public interface IComments_Presenter {

    void getComments(String id);

    void setView(IComments_View view);
}
