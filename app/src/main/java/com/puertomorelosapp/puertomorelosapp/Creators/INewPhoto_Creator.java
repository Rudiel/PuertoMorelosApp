package com.puertomorelosapp.puertomorelosapp.Creators;

import android.app.Dialog;

import com.puertomorelosapp.puertomorelosapp.Models.Request.Selfie;

/**
 * Created by rudielavilaperaza on 8/19/17.
 */

public interface INewPhoto_Creator {

    void onCancelClick(Dialog dialog);

    void onConfirmClick(Dialog dialog, byte bytes[], Selfie selfie);
}
