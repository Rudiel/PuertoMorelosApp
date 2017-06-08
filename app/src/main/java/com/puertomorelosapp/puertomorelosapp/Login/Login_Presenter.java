package com.puertomorelosapp.puertomorelosapp.Login;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.puertomorelosapp.puertomorelosapp.Models.User;
import com.puertomorelosapp.puertomorelosapp.Utils.PuertoMorelosApplication;

/**
 * Created by rudielavilaperaza on 6/8/17.
 */

public class Login_Presenter implements ILogin_Presenter {

    private ILogin_View view;


    public Login_Presenter(Context context) {
        ((PuertoMorelosApplication) context).getAppComponent().inject(this);
    }

    @Override
    public void setView(ILogin_View view) {
        this.view = view;
    }

    @Override
    public void starRegisterActivity() {
        view.showRegisterActivity();
    }

    @Override
    public void loginWithPassandUser(FirebaseAuth auth, User user) {
        view.showLoading();

        auth.signInWithEmailAndPassword(user.getEmail(), user.getPassword()).addOnCompleteListener((Activity) user.getContext(), new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    view.showMainActivity();
                } else {
                    view.showErrorMessage(task.getException().getMessage());
                }
                view.hideLoading();

            }
        });

    }
}
