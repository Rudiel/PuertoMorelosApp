package com.puertomorelosapp.puertomorelosapp.Fragments.Activities.Comments;

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
 * Created by rudielavilaperaza on 9/12/17.
 */

public class Comentarios_Presenter implements IComentarios_Presenter {

    private IComentarios_View view;

    public Comentarios_Presenter(Context context) {

        ((PuertoMorelosApplication) context).getAppComponent().inject(this);
    }

    @Override
    public void getComentarios(Context context) {

        view.showLoading();

        final List<Comments> commentsList = new ArrayList<>();

        FirebaseDatabase.getInstance().getReference().child(
                Utils.COMMENTS_SOCIAL_URL +
                        "/" +
                        Utils.getProvider(context))
                .addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        for (DataSnapshot data : dataSnapshot.getChildren()) {
                            Comments comment = data.getValue(Comments.class);
                            commentsList.add(comment);
                        }

                        view.hideLoading();
                        view.showComentarios(commentsList);

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

    }

    @Override
    public void setView(IComentarios_View view) {
        this.view = view;
    }
}
