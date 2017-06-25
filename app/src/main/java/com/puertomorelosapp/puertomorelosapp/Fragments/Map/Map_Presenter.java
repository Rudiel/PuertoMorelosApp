package com.puertomorelosapp.puertomorelosapp.Fragments.Map;

import android.content.Context;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.puertomorelosapp.puertomorelosapp.Models.Categorie;
import com.puertomorelosapp.puertomorelosapp.Utils.PuertoMorelosApplication;
import com.puertomorelosapp.puertomorelosapp.Utils.Utils;

/**
 * Created by rudielavilaperaza on 6/21/17.
 */

public class Map_Presenter implements IMap_Presenter {

    private IMap_View view;

    public Map_Presenter(Context context) {

        ((PuertoMorelosApplication) context).getAppComponent().inject(this);

    }

    @Override
    public void setView(IMap_View view) {
        this.view = view;
    }

    @Override
    public void getPlaces() {


        FirebaseDatabase database= FirebaseDatabase.getInstance();
        Log.d("REF",database.getReference().child(Utils.CATE_URL).toString());
        /*FirebaseDatabase.getInstance()
                .getReference()
                .child(Utils.CATE_URL_BACK+"/"+snapshot.getValue()+"/Background");*/

        FirebaseDatabase.getInstance().getReference().child(Utils.PLACES_URL)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            if(snapshot.hasChildren()){
                                for(DataSnapshot snap: snapshot.getChildren()){
                                    Log.d("snap",snap.getValue().toString());
                                    if(snap.hasChildren()){
                                        for(DataSnapshot s: snapshot.getChildren()){
                                            Log.d("s",s.getValue().toString());
                                        }
                                    }
                                }
                            }

                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });

    }
}
