package com.puertomorelosapp.puertomorelosapp.Creators;

import android.app.Dialog;

/**
 * Created by rudielavilaperaza on 6/28/17.
 */

public interface IConfirmDialog_Creator {

    void onAccept(Dialog dialog);

    void onCancel(Dialog dialog);
}
