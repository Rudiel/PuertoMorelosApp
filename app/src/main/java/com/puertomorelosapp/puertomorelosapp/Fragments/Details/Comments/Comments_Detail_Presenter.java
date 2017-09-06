package com.puertomorelosapp.puertomorelosapp.Fragments.Details.Comments;

import android.content.Context;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.puertomorelosapp.puertomorelosapp.Models.Categorie;
import com.puertomorelosapp.puertomorelosapp.Models.Request.NewComment;
import com.puertomorelosapp.puertomorelosapp.Models.Request.RoutesComments;
import com.puertomorelosapp.puertomorelosapp.Models.Response.Comments;
import com.puertomorelosapp.puertomorelosapp.Models.SubCategory;
import com.puertomorelosapp.puertomorelosapp.Utils.PuertoMorelosApplication;
import com.puertomorelosapp.puertomorelosapp.Utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rudielavilaperaza on 8/12/17.
 */

public class Comments_Detail_Presenter implements IComments_Presenter {

    private IComments_View view;

    private DatabaseReference commentsReference;

    public Comments_Detail_Presenter(Context context) {

        ((PuertoMorelosApplication) context).getAppComponent().inject(this);
    }

    @Override
    public void getComments(final String id) {
        view.showLoading();

        final List<Comments> commentsList = new ArrayList<>();

        commentsReference.child(Utils.COMMENTS_SOCIAL_URL).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Log.d("CHANGED","VALUE WAS CHANGED");
                for (DataSnapshot data : dataSnapshot.getChildren()) {

                    for (DataSnapshot d : data.getChildren()) {

                        Comments comment = d.getValue(Comments.class);

                        if (comment.getItemKey().equals(id))
                            commentsList.add(comment);

                    }


                }

                view.setComments(commentsList);
                view.hideLoading();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void setView(IComments_View view) {

        this.view = view;

    }

    @Override
    public void setNewComment(NewComment comment, RoutesComments routesComments) {

        //primera ruta

        commentsReference.child(routesComments.getUrl1()).push().setValue(comment);
        //segunda ruta

        commentsReference.child(routesComments.getUrl2()).push().setValue(comment);

        //tercera ruta

       commentsReference.child(routesComments.getUrl3()).push().setValue("true");

        //una vez que lo guarde refrescamos la lista
        view.onCommentSucces();


    }

    @Override
    public void setDatabaseReference(DatabaseReference reference) {
        this.commentsReference = reference;
    }

}
