package com.puertomorelosapp.puertomorelosapp.Fragments.Details.Comments;

import com.puertomorelosapp.puertomorelosapp.Models.Response.Comments;
import com.puertomorelosapp.puertomorelosapp.Models.Response.ProfileInfo;

import java.util.List;

/**
 * Created by rudielavilaperaza on 8/12/17.
 */

public interface IComments_View {

    void showLoading();

    void hideLoading();

    void setComments(List<Comments> commentList);

    void showProfileInfo(ProfileInfo profile);

}
