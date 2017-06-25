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

    public SecundaryMain_Presenter(Context context) {
        ((PuertoMorelosApplication) context).getAppComponent().inject(this);
    }


    @Override
    public void getSubCategories(Categorie categorie) {
        this.view.showLoading();

        final List<SubCategory> listSubCategories = new ArrayList<>();

        FirebaseDatabase.getInstance().getReference().child(Utils.PLACES_URL + "/" + categorie.getName()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Log.d("VALUE", snapshot.child("descripcion").getValue().toString());
                    SubCategory subCategory = new SubCategory();
                    subCategory.setActivo(snapshot.child("activo").getValue().toString());
                    subCategory.setComentariosCantidad(Integer.parseInt(snapshot.child("comentariosCantidad").getValue().toString()));
                    subCategory.setDescripcion(snapshot.child("descripcion").getValue().toString());
                    subCategory.setDescripcion2(snapshot.child("descripcion2").getValue().toString());
                    subCategory.setDescripcion3(snapshot.child("descripcion3").getValue().toString());
                    subCategory.setDireccion(snapshot.child("direccion").getValue().toString());
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

                    listSubCategories.add(subCategory);
                }

                view.showSubCategories(listSubCategories);
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
}
