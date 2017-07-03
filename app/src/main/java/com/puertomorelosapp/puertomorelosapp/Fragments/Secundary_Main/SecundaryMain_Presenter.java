package com.puertomorelosapp.puertomorelosapp.Fragments.Secundary_Main;

import android.content.Context;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.puertomorelosapp.puertomorelosapp.Models.Categorie;
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


    public SecundaryMain_Presenter(Context context) {
        ((PuertoMorelosApplication) context).getAppComponent().inject(this);
    }


    @Override
    public void getSubCategories(final Categorie categorie) {
        this.view.showLoading();


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


        FirebaseDatabase.getInstance().getReference().child(url_reference).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                List<SubCategory> listSubCategories = new ArrayList<>();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {


                    Log.d("VALUE", snapshot.getChildren().toString());
                    total = (int) snapshot.getChildrenCount();
                    SubCategory subCategory = new SubCategory();
                    subCategory.setId(snapshot.getKey());
                    subCategory.setActivo(Integer.parseInt(snapshot.child("activo").getValue().toString()));
                    subCategory.setComentariosCantidad(Integer.parseInt(snapshot.child("comentariosCantidad").getValue().toString()));
                    subCategory.setDescripcion(snapshot.child("descripcion").getValue().toString());
                    subCategory.setDescripcion2(snapshot.child("descripcion2").getValue().toString());
                    subCategory.setDescripcion3(snapshot.child("descripcion3").getValue().toString());
                    subCategory.setDireccion(snapshot.child("direccion").getValue().toString());

                    try {
                        subCategory.setFechadias(snapshot.child("fechadias").getValue().toString());
                        subCategory.setHorafin(snapshot.child("horafin").getValue().toString());
                        subCategory.setHorainicio(snapshot.child("horainicio").getValue().toString());
                        subCategory.setAcceso(snapshot.child("acceso").getValue().toString());

                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    subCategory.setImageBackgroundContent(snapshot.child("imageBackgroundContent").getValue().toString());
                    subCategory.setImageHeader(snapshot.child("imageHeader").getValue().toString());
                    subCategory.setLatitud(snapshot.child("latitud").getValue().toString());
                    subCategory.setLongitud(snapshot.child("longitud").getValue().toString());
                    subCategory.setLoveCantidad(Integer.parseInt(snapshot.child("loveCantidad").getValue().toString()));
                    subCategory.setNombre(snapshot.child("nombre").getValue().toString());
                    subCategory.setPrioridad(Integer.parseInt(snapshot.child("prioridad").getValue().toString()));
                    subCategory.setTags(snapshot.child("tags").getValue().toString());
                    subCategory.setTelefono(snapshot.child("telefono").getValue().toString());
                    subCategory.setTitulo(snapshot.child("titulo").getValue().toString());
                    subCategory.setTitulo2(snapshot.child("titulo2").getValue().toString());
                    subCategory.setTitulo3(snapshot.child("titulo3").getValue().toString());
                    getCommnets(categorie.getName(), subCategory.getId(), subCategory, listSubCategories);

                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });


    }

    private void getCommnets(final String category, final String id, final SubCategory subCategory, final List<SubCategory> subCategories) {
        FirebaseDatabase.getInstance().getReference().child(Utils.COMMENTS_URL + category + "/" + id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("COMMENTS", String.valueOf(dataSnapshot.getChildrenCount()));
                subCategory.setComments((int) dataSnapshot.getChildrenCount());
                getLikes(category, id, subCategory, subCategories);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void getLikes(String category, String id, final SubCategory subCategory, final List<SubCategory> subCategories) {
        FirebaseDatabase.getInstance().getReference().child(Utils.LIKES_URL + category + "/" + id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Log.d("LIKES", String.valueOf(dataSnapshot.getChildrenCount()));

                subCategory.setLikes((int) dataSnapshot.getChildrenCount());

                subCategories.add(subCategory);

                view.showSubCategories(subCategories);
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
}
