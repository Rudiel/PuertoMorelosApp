package com.puertomorelosapp.puertomorelosapp.Register;

/**
 * Created by rudielavilaperaza on 6/8/17.
 */

public interface IRegister_View {

    void showLoading();

    void hideLoading();

    void registerDone();

    void showMessageError(String message);
}
