package com.puertomorelosapp.puertomorelosapp.Fragments.Details;

/**
 * Created by rudielavilaperaza on 6/29/17.
 */

public interface IDetail_View {

    void showLoading();

    void hideLoading();

    void setPhotosNumber(int photosNumber);

    void isLikeActive(boolean like);

    void setLikesNumber(int likesNumber);

    void setCommentsNumber(int commentsNumber);

    void setCommentedbyUser(boolean isAlreadyCommented);

    void setPhotobyUser(boolean isAlreadyPhoted);

}
