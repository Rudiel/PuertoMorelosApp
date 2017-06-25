package com.puertomorelosapp.puertomorelosapp.Fragments.Categories;

import com.puertomorelosapp.puertomorelosapp.Models.Categorie;

import java.util.List;

/**
 * Created by rudielavilaperaza on 6/13/17.
 */

public interface ICategories_View {

    void showLoading();

    void hideLoading();

    void showCategories(List<Categorie> categories);

}
