package com.puertomorelosapp.puertomorelosapp.Creators;

import android.app.Dialog;
import android.support.v7.app.AlertDialog;

/**
 * Created by rudielavilaperaza on 6/12/17.
 */

public interface IDialog_Creator {

    void didConfirm(Dialog dialog);

    void didCancel(Dialog dialog);

    void didOK(Dialog dialog);
}
