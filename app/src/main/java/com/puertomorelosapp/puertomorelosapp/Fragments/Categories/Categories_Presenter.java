package com.puertomorelosapp.puertomorelosapp.Fragments.Categories;

import android.content.Context;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
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

    String image = null;


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

        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        reference.child(Utils.CATE_URL).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {

                for (final DataSnapshot data : dataSnapshot.getChildren()) {

                    final Categorie categoria = new Categorie();
                    categoria.setName(data.getValue().toString());

                    String URL;

                    switch (categoria.getName()) {
                        case "Hoteles":
                            URL = Utils.CATE_URL_BACK + "/Servicios/" + categoria.getName() + "/Background";
                            break;
                        case "Restaurantes":
                        case "Comida rapida":
                            URL = Utils.CATE_URL_BACK + "/Comercios/" + categoria.getName() + "/Background";
                            break;
                        default:
                            URL = Utils.CATE_URL_BACK + "/" + categoria.getName() + "/Background";
                            break;
                    }

                    reference.child(URL)
                            .addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot1) {

                                    categoria.setImage(dataSnapshot1.getValue().toString());

                                    categorieList.add(categoria);

                                    if (dataSnapshot.getChildrenCount() == categorieList.size()) {
                                        view.hideLoading();
                                        view.showCategories(categorieList);
                                    }

                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }


}
