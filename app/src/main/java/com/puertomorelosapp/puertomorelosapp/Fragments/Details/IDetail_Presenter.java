package com.puertomorelosapp.puertomorelosapp.Fragments.Details;

/**
 * Created by rudielavilaperaza on 6/29/17.
 */

public interface IDetail_Presenter {

    void setView(IDetail_View view);

    void getNumberComments(String CategoriaPrincipal, String Categoria, String subCategoria);

    void getNumberLikes(String CategoriaPrincipal, String Categoria, String subCategoria);

}
