package com.puertomorelosapp.puertomorelosapp.Fragments.Details.Comments;


import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.puertomorelosapp.puertomorelosapp.Models.Request.NewComment;
import com.puertomorelosapp.puertomorelosapp.Models.Request.RoutesComments;

/**
 * Created by rudielavilaperaza on 8/12/17.
 */

public interface IComments_Presenter {

    void getComments(String id);

    void setView(IComments_View view);

    void setNewComment(NewComment comment, RoutesComments routesComments);

    void setDatabaseReference(DatabaseReference reference);

}
