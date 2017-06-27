package com.puertomorelosapp.puertomorelosapp.Fragments.Thrid_Main;

import android.content.Context;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.puertomorelosapp.puertomorelosapp.Models.Categorie;
import com.puertomorelosapp.puertomorelosapp.Models.ThirdCategory;
import com.puertomorelosapp.puertomorelosapp.Utils.PuertoMorelosApplication;
import com.puertomorelosapp.puertomorelosapp.Utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rudielavilaperaza on 6/27/17.
 */

public class Third_Main_Presenter implements IThird_Main_Presenter {

    private IThird_View view;

    public Third_Main_Presenter(Context context) {

        ((PuertoMorelosApplication) context).getAppComponent().inject(this);
    }

    @Override
    public void setView(IThird_View view) {
        this.view = view;
    }

    @Override
    public void getThirdCategories(final Categorie categorie) {

        view.showLoading();

        final List<ThirdCategory> thirdCategoryList = new ArrayList<>();

        FirebaseDatabase.getInstance().getReference().child(Utils.PLACES_URL + "/" + categorie.getName()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Log.d("THIRT", snapshot.getKey());

                    final ThirdCategory thirdCategory = new ThirdCategory();
                    thirdCategory.setName(snapshot.getKey());

                    FirebaseDatabase.getInstance().getReference().child(Utils.CATE_URL_BACK+"/"+categorie.getName()+"/"+snapshot.getKey()+"/Background").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot data) {

                            thirdCategory.setImage(data.getValue().toString());

                            thirdCategoryList.add(thirdCategory);

                            if (dataSnapshot.getChildrenCount() == thirdCategoryList.size()) {
                                view.hideLoading();
                                view.showThridCategories(thirdCategoryList);
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });



                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
