package com.puertomorelosapp.puertomorelosapp.Fragments.Categories;

import android.content.Context;

import com.puertomorelosapp.puertomorelosapp.Models.Categorie;
import com.puertomorelosapp.puertomorelosapp.Utils.PuertoMorelosApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rudielavilaperaza on 6/13/17.
 */

public class Categories_Presenter implements ICategories_Presenter {

    private ICategories_View view;

    public Categories_Presenter(Context context) {

        ((PuertoMorelosApplication) context).getAppComponent().inject(this);
    }

    @Override
    public void setView(ICategories_View view) {
        this.view = view;
    }

    @Override
    public void getCategories() {

        List<Categorie> categorieList = new ArrayList<>();

        Categorie eventos= new Categorie();
        eventos.setName("Eventos");

        Categorie historia= new Categorie();
        historia.setName("Historia");

        Categorie lugares= new Categorie();
        lugares.setName("Lugares");

        Categorie restaurantes= new Categorie();
        restaurantes.setName("Restaurantes");

        Categorie gym= new Categorie();
        gym.setName("Gym");

        categorieList.add(eventos);
        categorieList.add(historia);
        categorieList.add(lugares);
        categorieList.add(restaurantes);
        categorieList.add(gym);

        view.showCategories(categorieList);
    }


}
