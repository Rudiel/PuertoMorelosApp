package com.puertomorelosapp.puertomorelosapp.Fragments.Details.Photos;

import android.content.Context;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.puertomorelosapp.puertomorelosapp.Models.Categorie;
import com.puertomorelosapp.puertomorelosapp.Models.Request.Gallery;
import com.puertomorelosapp.puertomorelosapp.Models.Request.Selfie;
import com.puertomorelosapp.puertomorelosapp.Models.SubCategory;
import com.puertomorelosapp.puertomorelosapp.Utils.PuertoMorelosApplication;
import com.puertomorelosapp.puertomorelosapp.Utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rudielavilaperaza on 8/15/17.
 */

public class Photos_Detail_Presenter implements IPhotos_Presenter {

    IPhotos_View view;

    public Photos_Detail_Presenter(Context context) {

        ((PuertoMorelosApplication) context).getAppComponent().inject(this);

    }

    @Override
    public void getSelfies(Categorie categorie, SubCategory subCategory) {

        view.showLoading();
        String Url = "";
        final List<Selfie> selfieList = new ArrayList<>();

        if (categorie.getCategoria() == null) {
            Url = Utils.SELFIES_URL + categorie.getName() + "/" + subCategory.getId();
        } else {
            Url = Utils.SELFIES_URL + categorie.getCategoria() + "/" + categorie.getName() + "/" + subCategory.getId();
        }

        //Obtenemos las selfies
        FirebaseDatabase.getInstance().getReference(Url).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot data : dataSnapshot.getChildren()) {

                    Selfie selfie = new Selfie();
                    selfie = data.getValue(Selfie.class);

                    selfieList.add(selfie);

                }


                view.showPhotos(selfieList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

    }

    @Override
    public void getGallery(Categorie categorie, SubCategory subCategory) {

        view.showLoading();
        String Url = "";
        final List<Gallery> galleryList = new ArrayList<>();

        if (categorie.getCategoria() == null) {
            Url = Utils.GALLERY_URL + categorie.getName() + "/" + subCategory.getId();
        } else {
            Url = Utils.GALLERY_URL + categorie.getCategoria() + "/" + categorie.getName() + "/" + subCategory.getId();
        }

        //Obtenemos las selfies
        FirebaseDatabase.getInstance().getReference(Url).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot data : dataSnapshot.getChildren()) {

                    Gallery gallery = new Gallery();
                    gallery = data.getValue(Gallery.class);

                    galleryList.add(gallery);

                }


                view.showGallery(galleryList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

    }

    @Override
    public void setView(IPhotos_View view) {
        this.view = view;
    }
}
