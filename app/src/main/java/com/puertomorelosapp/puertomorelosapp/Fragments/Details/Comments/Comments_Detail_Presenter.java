package com.puertomorelosapp.puertomorelosapp.Fragments.Details.Comments;

import android.content.Context;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.puertomorelosapp.puertomorelosapp.Models.Request.NewComment;
import com.puertomorelosapp.puertomorelosapp.Models.Request.RoutesComments;
import com.puertomorelosapp.puertomorelosapp.Models.Response.Comments;
import com.puertomorelosapp.puertomorelosapp.Models.Response.ProfileInfo;
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

        FirebaseDatabase.getInstance().getReference().child(Utils.COMMENTS_SOCIAL_URL).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                final List<Comments> commentsList = new ArrayList<>();

                for (DataSnapshot data : dataSnapshot.getChildren()) {

                    for (DataSnapshot d : data.getChildren()) {

                        Comments comment = d.getValue(Comments.class);

                        if (comment.getItemKey().equals(id)) {
                            if (comment.getActivo() == 1)
                                commentsList.add(comment);
                        }

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

        String key = FirebaseDatabase.getInstance().getReference().child(routesComments.getUrl1()).push().getKey();

        //primera ruta
        FirebaseDatabase.getInstance().getReference().child(routesComments.getUrl2() + "/" + key).setValue(comment);

        //segunda ruta
        FirebaseDatabase.getInstance().getReference().child(routesComments.getUrl1() + "/" + key).setValue(comment);

        //tercera ruta
        FirebaseDatabase.getInstance().getReference().child(routesComments.getUrl3() + "/" + key).setValue("true");

    }

    @Override
    public void getProfileInfo(Comments comment) {
        final ProfileInfo info = new ProfileInfo();
        info.setName(comment.getSenderDisplayName());
        info.setImage(comment.getImageURL());

        FirebaseDatabase.getInstance().getReference().child(Utils.COMMENTS_COUNT + comment.getSenderId()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    if (snapshot.getKey().equals("UniversalComments")) {

                        info.setComments((int)snapshot.getChildrenCount());

                    }else if(snapshot.getKey().equals("UniversalLoves")){

                        info.setLikes((int)snapshot.getChildrenCount());

                    }
                    else if(snapshot.getKey().equals("UniversalSelfies")){

                        info.setSelfies((int)snapshot.getChildrenCount());

                    }
                }
                view.showProfileInfo(info);
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

}
