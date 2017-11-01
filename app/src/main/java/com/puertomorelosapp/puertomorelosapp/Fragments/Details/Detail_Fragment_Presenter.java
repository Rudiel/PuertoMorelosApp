package com.puertomorelosapp.puertomorelosapp.Fragments.Details;

import android.content.Context;
import android.util.Log;

import com.bumptech.glide.util.Util;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.puertomorelosapp.puertomorelosapp.Models.Categorie;
import com.puertomorelosapp.puertomorelosapp.Models.Request.Like;
import com.puertomorelosapp.puertomorelosapp.Models.Request.Selfie;
import com.puertomorelosapp.puertomorelosapp.Models.Response.Comments;
import com.puertomorelosapp.puertomorelosapp.Models.SubCategory;
import com.puertomorelosapp.puertomorelosapp.Utils.PuertoMorelosApplication;
import com.puertomorelosapp.puertomorelosapp.Utils.Utils;

import java.util.ArrayList;
import java.util.List;


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
                reference.child(Utils.LIKES_URL + "/" + like.getCategoria() + "/" + like.getSubcategoria() + "/" + like.getItemKey() + "/" + Utils.getProvider(context) + "/love").setValue(true);
            else
                reference.child(Utils.LIKES_URL + "/" + like.getCategoria() + "/" + like.getItemKey() + "/" + Utils.getProvider(context) + "/love").setValue(true);

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

    @Override
    public void getLikes(SubCategory subCategory, Categorie categorie) {
        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        String Url;

        if (categorie.getCategoria() == null) {
            Url = Utils.LIKES_URL + categorie.getName() + "/" + subCategory.getId();

        } else {
            Url = Utils.LIKES_URL + categorie.getCategoria() + "/" + categorie.getName() + "/" + subCategory.getId();
        }

        reference.child(Url).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                view.setLikesNumber((int) dataSnapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void getCommentsNumber(SubCategory subCategory, Categorie categorie) {

        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        String Url;

        if (categorie.getCategoria() == null) {
            Url = Utils.COMMENTS_URL + categorie.getName() + "/" + subCategory.getId();

        } else {
            Url = Utils.COMMENTS_URL + categorie.getCategoria() + "/" + categorie.getName() + "/" + subCategory.getId();
        }

        reference.child(Url).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                final List<Comments> commentsList = new ArrayList<>();

                for (DataSnapshot comentario : dataSnapshot.getChildren()) {
                    Comments comment = comentario.getValue(Comments.class);
                    if (comment.getActivo() == 1) {
                        commentsList.add(comment);
                    }
                }
                view.setCommentsNumber(commentsList.size());

                // view.setCommentsNumber((int) dataSnapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void isCommentedbyUser(final Context context, Categorie category, String placeId) {

        String url;

        if (category.getCategoria() != null) {
            url = Utils.COMMENTS_URL + "/" + category.getCategoria() + "/" + category.getName() + "/" + placeId;
        } else {
            url = Utils.COMMENTS_URL + "/" + category.getName() + "/" + placeId;
        }

        FirebaseDatabase.getInstance().getReference().child(url).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot.getChildrenCount() > 0) {

                    for (DataSnapshot comment : dataSnapshot.getChildren()) {
                        Comments c = comment.getValue(Comments.class);
                        if (c.getActivo() == 1 && c.getSenderId().equals(Utils.getProvider(context))) {
                            view.setCommentedbyUser(true);
                            break;
                        }
                        view.setCommentedbyUser(false);
                    }

                } else
                    view.setCommentedbyUser(false);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void isPhotobyUser(final Context context, Categorie category, String placeId) {

        String url;

        if (category.getCategoria() != null) {
            url = Utils.SELFIES_URL + "/" + category.getCategoria() + "/" + category.getName() + "/" + placeId;
        } else {
            url = Utils.SELFIES_URL + "/" + category.getName() + "/" + placeId;
        }

        FirebaseDatabase.getInstance().getReference().child(url).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getChildrenCount() > 0) {
                    for (DataSnapshot selfie : dataSnapshot.getChildren()) {
                        Selfie s = selfie.getValue(Selfie.class);

                        if (s.getIdphotographer().equals(Utils.getProvider(context))) {
                            view.setPhotobyUser(true);
                            break;
                        }
                        view.setPhotobyUser(false);
                    }
                } else {
                    view.setPhotobyUser(false);
                }
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
