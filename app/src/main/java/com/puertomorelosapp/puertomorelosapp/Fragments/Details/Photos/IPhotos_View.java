package com.puertomorelosapp.puertomorelosapp.Fragments.Details.Photos;

import com.puertomorelosapp.puertomorelosapp.Models.Request.Gallery;
import com.puertomorelosapp.puertomorelosapp.Models.Request.Selfie;

import java.util.List;

/**
 * Created by rudielavilaperaza on 8/15/17.
 */

public interface IPhotos_View {

    void showLoadingSelfie();

    void hideLoadingSelfie();

    void showLoadingGallery();

    void hideLoadingGallery();

    void showGallery(List<Gallery> galleryList);

    void showPhotos(List<Selfie> selfieList);

    void showUploadingSelfie();

    void hidelUploadingSelfie();

}
