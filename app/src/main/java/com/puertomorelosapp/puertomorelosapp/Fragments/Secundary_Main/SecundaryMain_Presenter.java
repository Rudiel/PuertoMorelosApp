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
import com.puertomorelosapp.puertomorelosapp.Models.SubCategory;
import com.puertomorelosapp.puertomorelosapp.Utils.PuertoMorelosApplication;
import com.puertomorelosapp.puertomorelosapp.Utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rudielavilaperaza on 6/24/17.
 */

public class SecundaryMain_Presenter implements ISecundaryMain_Presenter {

    private List<SubCategory> listSubCategories = new ArrayList<>();
    private ISecundaryMain_view view;
    private List<Integer> likes = new ArrayList<>();


    public SecundaryMain_Presenter(Context context) {
        ((PuertoMorelosApplication) context).getAppComponent().inject(this);
    }


    @Override
    public void getSubCategories(final Categorie categorie) {
        this.view.showLoading();

        likes.clear();

        String url_reference;

        if (categorie.getName().equals("Comida rapida") || categorie.getName().equals("Restaurantes")) {
            categorie.setCategoria("Comercios");
        } else if (categorie.getName().equals("Hoteles")) {
            categorie.setCategoria("Servicios");
        }

        if (categorie.getCategoria() != null) {
            url_reference = Utils.PLACES_URL + "/" + categorie.getCategoria() + "/" + categorie.getName();
        } else {
            url_reference = Utils.PLACES_URL + "/" + categorie.getName();
        }

        FirebaseDatabase.getInstance().getReference().child(url_reference).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot.getChildrenCount() > 0) {

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                        SubCategory subCategory = snapshot.getValue(SubCategory.class);

                        subCategory.setId(snapshot.getKey());

                        if (subCategory.getActivo() == 1)
                            listSubCategories.add(subCategory);

                    }

                    if (listSubCategories.size() > 0) {
                        for (SubCategory subCategory : listSubCategories) {

                            getNumberComments(categorie, subCategory.getId(), subCategory);

                            getNumberLikes(categorie, subCategory.getId(), subCategory);
                        }
                    } else
                        view.hideLoading();

                } else
                    view.hideLoading();


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

    @Override
    public void getAd(Categorie category) {

        String url = "";

        if (category.getCategoria() != null)
            url = Utils.ADS_URL + category.getCategoria() + "/" + category.getName();
        else
            url = Utils.ADS_URL + category.getName();


        FirebaseDatabase.getInstance().getReference().child(url + "/imageURL").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() != null) {

                    view.showAd(dataSnapshot.getValue().toString());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void getNumberComments(final Categorie categorie, final String subcategoria, final SubCategory subCategory) {


        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        String Url;

        if (categorie.getCategoria() == null) {
            Url = Utils.COMMENTS_URL + categorie.getName() + "/" + subcategoria;

        } else {
            Url = Utils.COMMENTS_URL + categorie.getCategoria() + "/" + categorie.getName() + "/" + subcategoria;
        }

        reference.child(Url)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(final DataSnapshot dataSnapshot) {

                        final List<Comments> commentsList = new ArrayList<>();

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

        reference.child(Url)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(final DataSnapshot dataSnapshot) {

                        subCategory.setLikes((int) dataSnapshot.getChildrenCount());

                        likes.add(1);

                        if (listSubCategories.size() == likes.size()) {
                            view.hideLoading();
                            view.showSubCategories(listSubCategories);
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

    }

}
