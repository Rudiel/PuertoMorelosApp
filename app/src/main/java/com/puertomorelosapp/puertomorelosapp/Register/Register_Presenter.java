package com.puertomorelosapp.puertomorelosapp.Register;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.puertomorelosapp.puertomorelosapp.Models.Register;
import com.puertomorelosapp.puertomorelosapp.Recover.IRecover_View;
import com.puertomorelosapp.puertomorelosapp.Utils.PuertoMorelosApplication;

/**
 * Created by rudielavilaperaza on 6/8/17.
 */

public class Register_Presenter implements IRegister_Presenter {

    private IRegister_View view;

    public Register_Presenter(Context context) {
        ((PuertoMorelosApplication) context).getAppComponent().inject(this);
    }

    @Override
    public void setView(IRegister_View view) {
        this.view = view;
    }

    @Override
    public void registerUser(Register register, FirebaseAuth auth) {
        view.showLoading();

        auth.createUserWithEmailAndPassword(register.getEmail(), register.getPassword()).addOnCompleteListener((Activity) register.getContext(), new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    view.registerDone();
                } else {
                    view.showMessageError(task.getException().getMessage());
                }

                view.hideLoading();
            }
        });
    }
}
