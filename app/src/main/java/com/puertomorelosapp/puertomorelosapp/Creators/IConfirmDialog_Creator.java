package com.puertomorelosapp.puertomorelosapp.Creators;

import android.app.AlertDialog;

/**
 * Created by rudielavilaperaza on 6/28/17.
 */

public interface IConfirmDialog_Creator {

    void onAccept(AlertDialog dialog);

    void onCancel(AlertDialog dialog);
}
