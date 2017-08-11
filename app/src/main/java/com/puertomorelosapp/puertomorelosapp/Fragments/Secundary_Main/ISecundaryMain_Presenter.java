package com.puertomorelosapp.puertomorelosapp.Fragments.Secundary_Main;

import com.puertomorelosapp.puertomorelosapp.Models.Categorie;

/**
 * Created by rudielavilaperaza on 6/24/17.
 */

public interface ISecundaryMain_Presenter {

    void getSubCategories(Categorie category, String mainCategory);

    void setView(ISecundaryMain_view view);

}
