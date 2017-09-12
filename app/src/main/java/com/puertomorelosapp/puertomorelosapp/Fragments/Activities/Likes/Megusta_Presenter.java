package com.puertomorelosapp.puertomorelosapp.Fragments.Activities.Likes;

import android.content.Context;

import com.bumptech.glide.util.Util;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.puertomorelosapp.puertomorelosapp.Models.Request.Like;
import com.puertomorelosapp.puertomorelosapp.Utils.PuertoMorelosApplication;
import com.puertomorelosapp.puertomorelosapp.Utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rudielavilaperaza on 9/11/17.
 */

public class Megusta_Presenter implements IMegusta_Presenter {

    IMegusta_View view;

    public Megusta_Presenter(Context context) {
        ((PuertoMorelosApplication) context).getAppComponent().inject(this);
    }

    @Override
    public void setView(IMegusta_View view) {
        this.view = view;
    }

    @Override
    public void getLikesList(Context context) {
        view.showLoading();

        final List<Like> likeList = new ArrayList<>();

        FirebaseDatabase.getInstance().getReference().child(
                Utils.LIKE_SOCIAL_USER_URL +
                        "/" +
                        Utils.getProvider(context))
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        for (DataSnapshot data : dataSnapshot.getChildren()) {
                            Like like = data.getValue(Like.class);

                            likeList.add(like);

                        }

                        view.hideLoading();
                        view.setLikesList(likeList);

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
        //view.setLikesList();
    }
}
