package com.puertomorelosapp.puertomorelosapp.Fragments.Details;

import android.content.Context;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.puertomorelosapp.puertomorelosapp.Fragments.Categories.ICategories_Presenter;
import com.puertomorelosapp.puertomorelosapp.Models.Categorie;
import com.puertomorelosapp.puertomorelosapp.Utils.PuertoMorelosApplication;
import com.puertomorelosapp.puertomorelosapp.Utils.Utils;

import java.net.URL;

/**
 * Created by rudielavilaperaza on 6/29/17.
 */

public class Detail_Fragment_Presenter implements IDetail_Presenter {

    private IDetail_View view;
    private int likesChilds = 0;
    private int commentsChilds = 0;


    public Detail_Fragment_Presenter(Context context) {
        ((PuertoMorelosApplication) context).getAppComponent().inject(this);
    }


    @Override
    public void setView(IDetail_View view) {
        this.view = view;
    }



}
