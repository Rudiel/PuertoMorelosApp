package com.puertomorelosapp.puertomorelosapp.Fragments.Secundary_Main;

import com.puertomorelosapp.puertomorelosapp.Models.Categorie;

/**
 * Created by rudielavilaperaza on 6/24/17.
 */

public interface ISecundaryMain_Presenter {

    void getSubCategories(Categorie category);

    void setView(ISecundaryMain_view view);

    void getNumberLikes(Categorie category);

    void getNumberComments(Categorie category);

    void getAd(Categorie category);

}
