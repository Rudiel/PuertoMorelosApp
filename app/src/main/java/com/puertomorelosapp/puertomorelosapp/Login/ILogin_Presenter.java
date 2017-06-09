package com.puertomorelosapp.puertomorelosapp.Login;

import com.google.firebase.auth.FirebaseAuth;
import com.puertomorelosapp.puertomorelosapp.Models.User;

/**
 * Created by rudielavilaperaza on 6/8/17.
 */

public interface ILogin_Presenter {

    void setView(ILogin_View view);

    void starRegisterActivity();

    void loginWithPassandUser(FirebaseAuth auth, User user);

    void startRecoveryActivity();
}
