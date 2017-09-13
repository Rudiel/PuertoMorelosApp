package com.puertomorelosapp.puertomorelosapp.Fragments.Map;

import android.content.Context;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.puertomorelosapp.puertomorelosapp.Models.Categorie;
import com.puertomorelosapp.puertomorelosapp.Models.Response.Place;
import com.puertomorelosapp.puertomorelosapp.Utils.PuertoMorelosApplication;
import com.puertomorelosapp.puertomorelosapp.Utils.Utils;

import java.util.ArrayList;
import java.util.List;

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

        final List<Place> listplaces = new ArrayList<>();

        FirebaseDatabase.getInstance().getReference().child(Utils.PLACES_URL)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        for (DataSnapshot categoria : dataSnapshot.getChildren()) {
                            if (!categoria.getKey().equals("Menu")) {
                                Log.d("MAP_CAT", categoria.getKey().toString());

                                if (!categoria.getKey().equals("Servicios") && !categoria.getKey().equals("Comercios")) {

                                    for (DataSnapshot item : categoria.getChildren()) {
                                        Place place = item.getValue(Place.class);
                                        place.setCategoria(categoria.getKey());
                                        listplaces.add(place);
                                    }

                                } else {
                                    //Recorremos cada subcategoria

                                    for (DataSnapshot subcategoria : categoria.getChildren()) {
                                        for (DataSnapshot item : subcategoria.getChildren()) {
                                            Place place = item.getValue(Place.class);
                                            place.setSubcategoria(subcategoria.getKey());
                                            place.setCategoria(categoria.getKey());

                                            listplaces.add(place);
                                        }
                                    }
                                }

                            }
                        }

                        view.showPlaces(listplaces);

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });

    }
}
