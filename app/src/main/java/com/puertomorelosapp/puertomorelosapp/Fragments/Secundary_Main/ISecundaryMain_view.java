package com.puertomorelosapp.puertomorelosapp.Fragments.Secundary_Main;

import com.puertomorelosapp.puertomorelosapp.Models.Categorie;
import com.puertomorelosapp.puertomorelosapp.Models.SubCategory;

import java.util.List;

/**
 * Created by rudielavilaperaza on 6/24/17.
 */

public interface ISecundaryMain_view {

    void hideLoading();

    void showLoading();

    void showSubCategories(List<SubCategory> subCategories);

    void updateAdapter();


}
