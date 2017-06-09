package com.puertomorelosapp.puertomorelosapp.Recover;

/**
 * Created by rudielavilaperaza on 6/8/17.
 */

public interface IRecover_View {

    void showLoading();

    void hideLoading();

    void showErrorMessage(String message);

    void onRecoveryAccountDone();
}
