package com.puertomorelosapp.puertomorelosapp.Main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.puertomorelosapp.puertomorelosapp.Models.Categorie;
import com.puertomorelosapp.puertomorelosapp.Utils.PuertoMorelosApplication;
import com.puertomorelosapp.puertomorelosapp.Utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rudielavilaperaza on 6/8/17.
 */

public class Main_Presenter implements IMain_Presenter {

    IMain_View view;

    public Main_Presenter(Context context) {
        ((PuertoMorelosApplication) context).getAppComponent().inject(this);

    }

    @Override
    public void setView(IMain_View view) {
        this.view = view;
    }


    @Override
    public void logout(FirebaseAuth auth) {

        view.logoutSesion();
    }

    @Override
    public void getMenuComments(String id) {

        FirebaseDatabase.getInstance().getReference().child(Utils.COMMENTS_COUNT + id + "/" + "UniversalComments").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Log.d("MENU_COMMENTS", "" + (int) dataSnapshot.getChildrenCount());
                view.setComments((int) dataSnapshot.getChildrenCount());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    @Override
    public void getMenuLikes(String id) {

        FirebaseDatabase.getInstance().getReference().child(Utils.COMMENTS_COUNT + id + "/UniversalLoves").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                view.setLikes((int) dataSnapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void getAd() {
        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        reference.child(Utils.WELCOME_AD).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                view.showAd(dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }


}
