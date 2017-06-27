package com.puertomorelosapp.puertomorelosapp.Fragments.Thrid_Main;

import com.puertomorelosapp.puertomorelosapp.Models.Categorie;

/**
 * Created by rudielavilaperaza on 6/27/17.
 */

public interface IThird_Main_Presenter {

    void setView(IThird_View view);

    void getThirdCategories(Categorie categorie);
}
