package com.puertomorelosapp.puertomorelosapp.Login;

/**
 * Created by rudielavilaperaza on 6/8/17.
 */

public interface ILogin_View {

    void showLoading();

    void hideLoading();

    void showRegisterActivity();

    void showMainActivity();

    void showErrorMessage(String message);

}
