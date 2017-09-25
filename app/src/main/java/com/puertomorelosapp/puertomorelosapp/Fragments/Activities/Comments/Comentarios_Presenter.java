package com.puertomorelosapp.puertomorelosapp.Fragments.Activities.Comments;

import android.content.Context;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.puertomorelosapp.puertomorelosapp.Models.Categorie;
import com.puertomorelosapp.puertomorelosapp.Models.Request.NewComment;
import com.puertomorelosapp.puertomorelosapp.Models.Response.Comments;
import com.puertomorelosapp.puertomorelosapp.Utils.PuertoMorelosApplication;
import com.puertomorelosapp.puertomorelosapp.Utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by rudielavilaperaza on 9/12/17.
 */

public class Comentarios_Presenter implements IComentarios_Presenter {

    private IComentarios_View view;

    public Comentarios_Presenter(Context context) {

        ((PuertoMorelosApplication) context).getAppComponent().inject(this);
    }

    @Override
    public void getComentarios(Context context) {

        view.showLoading();


        FirebaseDatabase.getInstance().getReference().child(
                Utils.COMMENTS_SOCIAL_URL +
                        "/" +
                        Utils.getProvider(context))
                .addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        final List<Comments> commentsList = new ArrayList<>();

                        for (DataSnapshot data : dataSnapshot.getChildren()) {
                            Comments comment = data.getValue(Comments.class);
                            comment.setCommentId(data.getKey());
                            if (comment.getActivo() == 1)
                                commentsList.add(comment);
                        }

                        view.hideLoading();
                        view.showComentarios(commentsList);

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

    }

    @Override
    public void setView(IComentarios_View view) {
        this.view = view;
    }

    @Override
    public void deleteComment(Context context, Comments comment) {

        String urlSocialComments = "";
        String urlSocialUser = "";

        if (comment.getSubcategoria() != null) {
            urlSocialComments = Utils.COMMENTS_URL + comment.getCategoria() + "/" + comment.getSubcategoria() + "/" + comment.getItemKey() + "/" + comment.getCommentId();
        } else {
            urlSocialComments = Utils.COMMENTS_URL + comment.getCategoria() + "/" + comment.getItemKey() + "/" + comment.getCommentId();
        }

        urlSocialUser = Utils.COMMENTS_SOCIAL_URL + "/" + comment.getSenderId() + "/" + comment.getCommentId();

        Map<String, Object> map = new HashMap<>();
        map.put("activo", 0);

        FirebaseDatabase.getInstance().getReference().child(urlSocialComments).updateChildren(map);

        FirebaseDatabase.getInstance().getReference().child(urlSocialUser).updateChildren(map);

        FirebaseDatabase.getInstance().getReference().child(Utils.COMMENTS_COUNT + comment.getSenderId() + "/UniversalComments/" + comment.getCommentId()).removeValue();


    }

    @Override
    public void editComment(Context context, Comments editComment) {

        String urlSocialComments = "";
        String urlSocialUser = "";

        if (editComment.getSubcategoria() != null) {
            urlSocialComments = Utils.COMMENTS_URL + editComment.getCategoria() + "/" + editComment.getSubcategoria() + "/" + editComment.getItemKey() + "/" + editComment.getCommentId();
        } else {
            urlSocialComments = Utils.COMMENTS_URL + editComment.getCategoria() + "/" + editComment.getItemKey() + "/" + editComment.getCommentId();
        }

        urlSocialUser = Utils.COMMENTS_SOCIAL_URL + "/" + editComment.getSenderId() + "/" + editComment.getCommentId();

        Map<String, Object> map = new HashMap<>();
        map.put("text", editComment.getText());

        FirebaseDatabase.getInstance().getReference().child(urlSocialComments).updateChildren(map);

        FirebaseDatabase.getInstance().getReference().child(urlSocialUser).updateChildren(map);

       // FirebaseDatabase.getInstance().getReference().child(Utils.COMMENTS_COUNT + editComment.getSenderId() + "/UniversalComments/" + editComment.getCommentId()).removeValue();*/
    }
}
