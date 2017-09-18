package com.puertomorelosapp.puertomorelosapp.Fragments.Details.Photos;

import com.puertomorelosapp.puertomorelosapp.Models.Categorie;
import com.puertomorelosapp.puertomorelosapp.Models.Request.Selfie;
import com.puertomorelosapp.puertomorelosapp.Models.SubCategory;

/**
 * Created by rudielavilaperaza on 8/15/17.
 */

public interface IPhotos_Presenter {

    void getSelfies(Categorie categorie, SubCategory subCategory);

    void getGallery(Categorie categorie, SubCategory subCategory);

    void setView(IPhotos_View view);

    void saveSelfie(byte[] thumb, byte[] original, Selfie selfie, String itemName);

}
