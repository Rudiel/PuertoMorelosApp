package com.puertomorelosapp.puertomorelosapp.Fragments.Secundary_Main;

import android.content.Context;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.puertomorelosapp.puertomorelosapp.Models.Categorie;
import com.puertomorelosapp.puertomorelosapp.Models.Response.Comments;
import com.puertomorelosapp.puertomorelosapp.Models.Servicio;
import com.puertomorelosapp.puertomorelosapp.Models.SubCategory;
import com.puertomorelosapp.puertomorelosapp.Utils.PuertoMorelosApplication;
import com.puertomorelosapp.puertomorelosapp.Utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rudielavilaperaza on 6/24/17.
 */

public class SecundaryMain_Presenter implements ISecundaryMain_Presenter {

    private ISecundaryMain_view view;
    private int total = 0;
    private List<SubCategory> listSubCategories = new ArrayList<>();


    public SecundaryMain_Presenter(Context context) {
        ((PuertoMorelosApplication) context).getAppComponent().inject(this);
    }


    @Override
    public void getSubCategories(final Categorie categorie) {
        this.view.showLoading();

        final List<SubCategory> subCategories = new ArrayList<>();

        String url_reference;

        if (categorie.getCategoria() != null) {
            url_reference = Utils.PLACES_URL + "/" + categorie.getCategoria() + "/" + categorie.getName();
        } else {
            switch (categorie.getName()) {
                case "Hoteles":
                    url_reference = Utils.PLACES_URL + "/Servicios/" + categorie.getName();
                    break;
                case "Restaurantes":
                case "Comida rapida":
                    url_reference = Utils.PLACES_URL + "/Comercios/" + categorie.getName();
                    break;
                default:
                    url_reference = Utils.PLACES_URL + "/" + categorie.getName();
                    break;
            }
        }

        FirebaseDatabase.getInstance().getReference().child(url_reference).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                total = (int) dataSnapshot.getChildrenCount();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    Log.d("VALUE", dataSnapshot.getChildren().toString());

                    SubCategory subCategory = snapshot.getValue(SubCategory.class);

                    subCategory.setId(snapshot.getKey());

                    listSubCategories.add(subCategory);

                    //addSubcategoryElements(snapshot,subCategory);

                    if (categorie.getName().equals("Comida rapida") || categorie.getName().equals("Restaurantes")) {
                        categorie.setCategoria("Comercios");
                    } else if (categorie.getName().equals("Hoteles")) {
                        categorie.setCategoria("Servicios");
                    }

                    getNumberComments(categorie, snapshot.getKey(), subCategory);

                    getNumberLikes(categorie, snapshot.getKey(), subCategory);
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

    }


    @Override
    public void setView(ISecundaryMain_view view) {
        this.view = view;
    }

    @Override
    public void getNumberLikes(final Categorie categorie) {


       /* FirebaseDatabase.getInstance().getReference().child("").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/

    }

    @Override
    public void getNumberComments(Categorie categorie) {

    }

    public void getNumberComments(final Categorie categorie, final String subcategoria, final SubCategory subCategory) {


        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        String Url;

        if (categorie.getCategoria() == null) {
            Url = Utils.COMMENTS_URL + categorie.getName() + "/" + subcategoria;

        } else {
            Url = Utils.COMMENTS_URL + categorie.getCategoria() + "/" + categorie.getName() + "/" + subcategoria;
        }

        Log.d("COMMENTS_URL", Url);

        final List<Comments> commentsList = new ArrayList<>();


        reference.child(Url)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(final DataSnapshot dataSnapshot) {

                        for (SubCategory sub : listSubCategories) {
                            if (sub.getId().equals(subcategoria)) {
                                for (DataSnapshot comentario : dataSnapshot.getChildren()) {
                                    Comments comment = comentario.getValue(Comments.class);
                                    if (comment.getActivo() == 1) {
                                        commentsList.add(comment);
                                    }
                                }
                                subCategory.setComments(commentsList.size());
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

    }

    public void getNumberLikes(Categorie categorie, final String Subcategoria, final SubCategory subCategory) {
        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        String Url;

        if (categorie.getCategoria() == null) {
            Url = Utils.LIKES_URL + categorie.getName() + "/" + Subcategoria;

        } else {
            Url = Utils.LIKES_URL + categorie.getCategoria() + "/" + categorie.getName() + "/" + Subcategoria;
        }

        Log.d("LIKES_URL", Url);

        reference.child(Url)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(final DataSnapshot dataSnapshot) {

                        for (SubCategory sub : listSubCategories) {
                            if (sub.getId().equals(Subcategoria)) {
                                subCategory.setLikes((int) dataSnapshot.getChildrenCount());
                            }
                        }

                        view.showSubCategories(listSubCategories);

                        view.hideLoading();

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

    }

}
