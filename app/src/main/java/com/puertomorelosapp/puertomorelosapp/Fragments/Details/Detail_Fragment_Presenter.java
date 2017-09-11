package com.puertomorelosapp.puertomorelosapp.Fragments.Details;

import android.content.Context;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.puertomorelosapp.puertomorelosapp.Models.Categorie;
import com.puertomorelosapp.puertomorelosapp.Models.Request.Like;
import com.puertomorelosapp.puertomorelosapp.Models.SubCategory;
import com.puertomorelosapp.puertomorelosapp.Utils.PuertoMorelosApplication;
import com.puertomorelosapp.puertomorelosapp.Utils.Utils;


/**
 * Created by rudielavilaperaza on 6/29/17.
 */

public class Detail_Fragment_Presenter implements IDetail_Presenter {

    private IDetail_View view;

    private SubCategory subCategory;
    private Categorie categorie;

    private Context context;


    public Detail_Fragment_Presenter(Context context) {
        this.context = context;
        ((PuertoMorelosApplication) context).getAppComponent().inject(this);
    }


    @Override
    public void setView(IDetail_View view) {
        this.view = view;
    }

    @Override
    public void getPhotosNumber(SubCategory subCategory, Categorie categorie) {

        view.showLoading();

        this.categorie = categorie;

        this.subCategory = subCategory;

        String Url;

        if (categorie.getCategoria() == null) {
            Url = Utils.GALLERY_URL + categorie.getName() + "/" + subCategory.getId();
        } else {
            Url = Utils.GALLERY_URL + categorie.getCategoria() + "/" + categorie.getName() + "/" + subCategory.getId();
        }

        //Obtenemos las selfies
        FirebaseDatabase.getInstance().getReference(Url).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                getSelfiesNumber((int) dataSnapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });


    }

    @Override
    public void saveLike(Like like, boolean isDelete) {

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        //salvamos el like en tres rutas o eliminamos el like en 3 rutas

        //1. puertomapp.SocialUSER.loves.KEYUSER.itemKey

        //2.puertomapp.PuertoMorelos.SocialAPP.Loves.categoria.subcategoria(En caso de tener).itemKey.KeyUser

        //3.puertomapp.userCountSocial.KeyUser.UniversalLoves.itemKey

        if (isDelete) {
            //Eliminamos el like
            reference.child(Utils.LIKE_SOCIAL_USER_URL + "/" + Utils.getProvider(context) + "/" + like.getItemKey()).removeValue();

            if (like.getSubcategoria() != null)
                reference.child(Utils.LIKES_URL + "/" + like.getCategoria() + "/" + like.getSubcategoria() + "/" + like.getItemKey() + "/" + Utils.getProvider(context)).removeValue();
            else
                reference.child(Utils.LIKES_URL + "/" + like.getCategoria() + "/" + like.getItemKey() + "/" + Utils.getProvider(context)).removeValue();

            reference.child(Utils.COMMENTS_COUNT + Utils.getProvider(context) + "/UniversalLoves/" + like.getItemKey()).removeValue();

        } else {
            //Creamos el like

            reference.child(Utils.LIKE_SOCIAL_USER_URL + "/" + Utils.getProvider(context) + "/" + like.getItemKey()).setValue(like);

            if (like.getSubcategoria() != null)
                reference.child(Utils.LIKES_URL + "/" + like.getCategoria() + "/" + like.getSubcategoria() + "/" + like.getItemKey() + "/" + Utils.getProvider(context)+"/love").setValue(true);
            else
                reference.child(Utils.LIKES_URL + "/" + like.getCategoria() + "/" + like.getItemKey() + "/" + Utils.getProvider(context)+"/love").setValue(true);

            reference.child(Utils.COMMENTS_COUNT + Utils.getProvider(context) + "/UniversalLoves/" + like.getItemKey()).setValue("true");
        }

    }

    @Override
    public void getLikeActive(String userID, String placeID) {
        FirebaseDatabase.getInstance().getReference().child("usersCountSocial/" + userID + "/UniversalLoves/" + placeID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() != null) {
                    view.isLikeActive(true);
                } else
                    view.isLikeActive(false);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void getSelfiesNumber(final int gallerynumber) {
        String Url;

        if (categorie.getCategoria() == null) {
            Url = Utils.SELFIES_URL + categorie.getName() + "/" + subCategory.getId();
        } else {
            Url = Utils.SELFIES_URL + categorie.getCategoria() + "/" + categorie.getName() + "/" + subCategory.getId();
        }

        //Obtenemos las selfies
        FirebaseDatabase.getInstance().getReference(Url).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                view.hideLoading();
                view.setPhotosNumber(gallerynumber + (int) dataSnapshot.getChildrenCount());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

    }


}
