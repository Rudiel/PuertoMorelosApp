package com.puertomorelosapp.puertomorelosapp.Fragments.Details;

import com.puertomorelosapp.puertomorelosapp.Models.Categorie;
import com.puertomorelosapp.puertomorelosapp.Models.Request.Like;
import com.puertomorelosapp.puertomorelosapp.Models.SubCategory;

/**
 * Created by rudielavilaperaza on 6/29/17.
 */

public interface IDetail_Presenter {

    void setView(IDetail_View view);

    void getPhotosNumber(SubCategory subCategory, Categorie categorie);

    void saveLike(Like like, boolean isDelete);

    void getLikeActive(String userID, String placeID);


}
