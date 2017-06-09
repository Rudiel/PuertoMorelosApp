package com.puertomorelosapp.puertomorelosapp.Recover;

import android.content.Context;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.puertomorelosapp.puertomorelosapp.Models.Recovery;
import com.puertomorelosapp.puertomorelosapp.Utils.PuertoMorelosApplication;

/**
 * Created by rudielavilaperaza on 6/8/17.
 */

public class Recover_Presenter implements IRecover_Presenter {

    IRecover_View view;

    public Recover_Presenter(Context context) {
        ((PuertoMorelosApplication) context).getAppComponent().inject(this);
    }

    @Override
    public void setView(IRecover_View view) {
        this.view = view;
    }

    @Override
    public void recoveryAccount(Recovery recovery, FirebaseAuth auth) {
        view.showLoading();
        auth.sendPasswordResetEmail(recovery.getEmail()).
                addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            view.onRecoveryAccountDone();
                        } else {
                            view.showErrorMessage(task.getException().getMessage());
                        }

                        view.hideLoading();
                    }
                });
    }

}
