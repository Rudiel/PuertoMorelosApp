package com.puertomorelosapp.puertomorelosapp.Fragments.Details.Comments;

import android.content.Context;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.puertomorelosapp.puertomorelosapp.Models.Response.Comments;
import com.puertomorelosapp.puertomorelosapp.Utils.PuertoMorelosApplication;
import com.puertomorelosapp.puertomorelosapp.Utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rudielavilaperaza on 8/12/17.
 */

public class Comments_Detail_Presenter implements IComments_Presenter {

    private IComments_View view;

    public Comments_Detail_Presenter(Context context) {

        ((PuertoMorelosApplication) context).getAppComponent().inject(this);
    }

    @Override
    public void getComments(final String id) {
        view.showLoading();

        final List<Comments> commentsList = new ArrayList<>();

        FirebaseDatabase.getInstance().getReference(Utils.COMMENTS_SOCIAL_URL).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
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
}
