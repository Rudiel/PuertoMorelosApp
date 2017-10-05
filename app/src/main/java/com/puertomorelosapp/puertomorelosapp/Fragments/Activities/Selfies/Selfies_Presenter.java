package com.puertomorelosapp.puertomorelosapp.Fragments.Activities.Selfies;

import android.content.Context;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.puertomorelosapp.puertomorelosapp.Models.Request.Selfie;
import com.puertomorelosapp.puertomorelosapp.Utils.PuertoMorelosApplication;
import com.puertomorelosapp.puertomorelosapp.Utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rudielavilaperaza on 9/14/17.
 */

public class Selfies_Presenter implements ISelfies_Presenter {

    private ISelfies_View view;

    public Selfies_Presenter(Context context) {

        ((PuertoMorelosApplication) context).getAppComponent().inject(this);

    }

    @Override
    public void getSelfies(Context context) {

        FirebaseDatabase.getInstance().getReference().child(Utils.SELFIES_URL_NEW + Utils.getProvider(context)).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                List<Selfie> selfieList = new ArrayList<>();

                for (DataSnapshot selfie : dataSnapshot.getChildren()) {
                    Selfie s = selfie.getValue(Selfie.class);
                    s.setId(selfie.getKey());
                    selfieList.add(s);
                }

                view.setSelfies(selfieList);
                view.hideLoading();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void setView(ISelfies_View view) {
        this.view = view;
    }

    @Override
    public void deleteSelfie(Selfie selfie, Context context) {

        FirebaseDatabase.getInstance().getReference().child(Utils.SELFIES_URL_NEW + Utils.getProvider(context) + "/" + selfie.getId()).removeValue();

        FirebaseDatabase.getInstance().getReference().child(Utils.COMMENTS_COUNT + Utils.getProvider(context) + "/UniversalSelfies/" + selfie.getId()).removeValue();

        if (selfie.getSubcategoria() != null)
            FirebaseDatabase.getInstance().getReference().child(Utils.SELFIES_URL + selfie.getCategoria() + "/" + selfie.getSubcategoria() + "/" + selfie.getItemKey() + "/" + selfie.getId()).removeValue();
        else
            FirebaseDatabase.getInstance().getReference().child(Utils.SELFIES_URL + selfie.getCategoria() + "/" + selfie.getItemKey() + "/" + selfie.getId()).removeValue();


    }
}
