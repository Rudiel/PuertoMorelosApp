package com.puertomorelosapp.puertomorelosapp.Login;

import com.puertomorelosapp.puertomorelosapp.Models.Response.User;

/**
 * Created by rudielavilaperaza on 6/8/17.
 */

public interface ILogin_View {

    void showLoading();

    void hideLoading();

    void showRegisterActivity();

    void showMainActivity();

    void showErrorMessage(String message);

    void showRecoveryActivity();

    void saveUserData(User user);

    void showMainActivityAnonymous(User user);

}
