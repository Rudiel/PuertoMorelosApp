package com.puertomorelosapp.puertomorelosapp.Fragments.Details.Photos;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.storage.StorageManager;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.puertomorelosapp.puertomorelosapp.Models.Categorie;
import com.puertomorelosapp.puertomorelosapp.Models.Request.Gallery;
import com.puertomorelosapp.puertomorelosapp.Models.Request.Selfie;
import com.puertomorelosapp.puertomorelosapp.Models.Response.Photographer;
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
    private Context context;

    public Photos_Detail_Presenter(Context context) {

        ((PuertoMorelosApplication) context).getAppComponent().inject(this);

        this.context = context;

    }

    @Override
    public void getSelfies(Categorie categorie, SubCategory subCategory) {

        view.showLoadingSelfie();
        String Url = "";

        if (categorie.getCategoria() == null) {
            Url = Utils.SELFIES_URL + categorie.getName() + "/" + subCategory.getId();
        } else {
            Url = Utils.SELFIES_URL + categorie.getCategoria() + "/" + categorie.getName() + "/" + subCategory.getId();
        }

        //Obtenemos las selfies
        FirebaseDatabase.getInstance().getReference(Url).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {

                final List<Selfie> selfieList = new ArrayList<>();

                if (dataSnapshot.getChildrenCount() > 0) {

                    for (DataSnapshot data : dataSnapshot.getChildren()) {

                        Selfie selfie = new Selfie();
                        selfie = data.getValue(Selfie.class);

                        final Selfie finalSelfie = selfie;
                        FirebaseDatabase.getInstance().getReference(Utils.USERS_URL + "/" + selfie.getIdphotographer()).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot1) {

                                Photographer photographer =
                                        dataSnapshot1.getValue(Photographer.class);

                                finalSelfie.setPhotographer(photographer);

                                selfieList.add(finalSelfie);

                                if (selfieList.size() == dataSnapshot.getChildrenCount()) {
                                    view.hideLoadingSelfie();
                                    view.showPhotos(selfieList);
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });

                    }
                } else {
                    view.hideLoadingSelfie();
                    view.showPhotos(selfieList);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

    }

    @Override
    public void getGallery(Categorie categorie, SubCategory subCategory) {

        view.showLoadingGallery();
        String Url = "";

        if (categorie.getCategoria() == null) {
            Url = Utils.GALLERY_URL + categorie.getName() + "/" + subCategory.getId();
        } else {
            Url = Utils.GALLERY_URL + categorie.getCategoria() + "/" + categorie.getName() + "/" + subCategory.getId();
        }

        //Obtenemos las selfies
        FirebaseDatabase.getInstance().getReference(Url).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                final List<Gallery> galleryList = new ArrayList<>();

                for (DataSnapshot data : dataSnapshot.getChildren()) {

                    Gallery gallery = new Gallery();
                    gallery = data.getValue(Gallery.class);

                    galleryList.add(gallery);

                }

                view.hideLoadingGallery();
                view.showGallery(galleryList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

    }

    private void getPhotographer(final Selfie selfie, final List<Selfie> selfieList) {
        FirebaseDatabase.getInstance().getReference(Utils.USERS_URL + "/" + selfie.getIdphotographer()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Photographer photographer =
                        dataSnapshot.getValue(Photographer.class);

                selfie.setPhotographer(photographer);

                selfieList.add(selfie);


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

    @Override
    public void saveSelfie(final byte[] bytesThumb, byte[] bytes, final Selfie selfie, final String itemName) {

        view.showUploadingSelfie();

        StorageReference storage = FirebaseStorage.getInstance().getReference("/PuertoMorelos/selfies/" + itemName + "/" + "(" + selfie.getTimeStamp() + ").jpg");

        final UploadTask uploadTask = storage.putBytes(bytes);

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                //mostrar mensaje de que fallo

            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                //Esto es por que se tiene que guardar la imagen thumb
                String url = taskSnapshot.getDownloadUrl().toString();

                selfie.setSelfieOriginal(url);

                StorageReference storageThumb = FirebaseStorage.getInstance().getReference("/PuertoMorelos/selfies/" + itemName + "/" + "(" + System.currentTimeMillis() + ").jpg");

                UploadTask uploadTaskThumb = storageThumb.putBytes(bytesThumb);

                uploadTaskThumb.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        //mostrar mensaje de que fallo

                    }
                }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot1) {

                        String url = taskSnapshot1.getDownloadUrl().toString();

                        Bitmap bitmap = BitmapFactory.decodeByteArray(bytesThumb, 0, bytesThumb.length);

                        selfie.setHeight(((double) bitmap.getHeight())/10);
                        selfie.setWidth(((double) bitmap.getWidth())/10);

                        selfie.setSelfieThumb(url);

                        saveSelfie(selfie);
                    }
                });

            }
        });


    }

    private void saveSelfie(Selfie selfie) {

        String key = FirebaseDatabase.getInstance().getReference().child(Utils.SELFIES_URL_NEW + Utils.getProvider(context)).push().getKey();

        if (selfie.getSubcategoria() == null) {

            FirebaseDatabase.getInstance().getReference().child(Utils.SELFIES_URL + "/" + selfie.getCategoria() + "/" + selfie.getItemKey() + "/" + key).setValue(selfie);

        } else {
            FirebaseDatabase.getInstance().getReference().child(Utils.SELFIES_URL + "/" + selfie.getCategoria() + "/" + selfie.getSubcategoria() + "/" + selfie.getItemKey() + "/" + key).setValue(selfie);

        }

        FirebaseDatabase.getInstance().getReference().child(Utils.COMMENTS_COUNT + Utils.getProvider(context) + "/UniversalSelfies/" + key).setValue("true");

        FirebaseDatabase.getInstance().getReference().child(Utils.SELFIES_URL_NEW + Utils.getProvider(context) + "/" + key).setValue(selfie);

        view.hidelUploadingSelfie();


    }
}
