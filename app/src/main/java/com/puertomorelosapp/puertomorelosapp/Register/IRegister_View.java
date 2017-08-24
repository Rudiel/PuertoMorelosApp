package com.puertomorelosapp.puertomorelosapp.Register;

import com.puertomorelosapp.puertomorelosapp.Models.Response.User;

/**
 * Created by rudielavilaperaza on 6/8/17.
 */

public interface IRegister_View {

    void showLoading();

    void hideLoading();

    void registerDone(User user);

    void showMessageError(String message);

}
