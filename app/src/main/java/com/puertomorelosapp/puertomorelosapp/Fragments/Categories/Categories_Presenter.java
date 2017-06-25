package com.puertomorelosapp.puertomorelosapp.Fragments.Categories;

import android.content.Context;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.puertomorelosapp.puertomorelosapp.Main.Main_Activity;
import com.puertomorelosapp.puertomorelosapp.Models.Categorie;
import com.puertomorelosapp.puertomorelosapp.Utils.PuertoMorelosApplication;
import com.puertomorelosapp.puertomorelosapp.Utils.Utils;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by rudielavilaperaza on 6/13/17.
 */

public class Categories_Presenter implements ICategories_Presenter {

    private ICategories_View view;


    public Categories_Presenter(Context context) {

        ((PuertoMorelosApplication) context).getAppComponent().inject(this);
    }

    @Override
    public void setView(ICategories_View view) {
        this.view = view;
    }

    @Override
    public void getCategories(Context context) {

        view.showLoading();

        final List<Categorie> categorieList = new ArrayList<>();

        FirebaseDatabase.getInstance().getReference().child(Utils.CATE_URL)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            final Categorie categorie = new Categorie();
                            categorie.setName(snapshot.getValue().toString());

                            if (snapshot.getValue().toString().equals("Hoteles")) {
                                FirebaseDatabase.getInstance().getReference().child(Utils.CATE_URL_BACK + "/Servicios/" + snapshot.getValue().toString() + "/Background")
                                        .addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(DataSnapshot data) {
                                                categorie.setImage(data.getValue().toString());
                                            }

                                            @Override
                                            public void onCancelled(DatabaseError databaseError) {

                                            }
                                        });
                            } else if (snapshot.getValue().toString().equals("Restaurantes") ||
                                    snapshot.getValue().toString().equals("Comida rapida")) {

                                FirebaseDatabase.getInstance().getReference().child(Utils.CATE_URL_BACK + "/Comercios/" + snapshot.getValue().toString() + "/Background")
                                        .addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(DataSnapshot data) {
                                                categorie.setImage(data.getValue().toString());
                                            }

                                            @Override
                                            public void onCancelled(DatabaseError databaseError) {

                                            }
                                        });
                            } else {
                                FirebaseDatabase.getInstance().getReference().child(Utils.CATE_URL_BACK + "/" + snapshot.getValue().toString() + "/Background")
                                        .addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(DataSnapshot data) {
                                                categorie.setImage(data.getValue().toString());
                                            }

                                            @Override
                                            public void onCancelled(DatabaseError databaseError) {

                                            }
                                        });
                            }


                            categorieList.add(categorie);
                        }

                        view.hideLoading();
                        view.showCategories(categorieList);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });

    }


}
