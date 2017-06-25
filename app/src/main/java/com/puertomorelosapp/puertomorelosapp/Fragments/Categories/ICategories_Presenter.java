package com.puertomorelosapp.puertomorelosapp.Fragments.Categories;

import android.content.Context;

import com.puertomorelosapp.puertomorelosapp.Models.Categorie;

import java.util.List;

/**
 * Created by rudielavilaperaza on 6/13/17.
 */

public interface ICategories_Presenter {

    void setView(ICategories_View view);

    void getCategories(Context context);

}
