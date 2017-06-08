package com.puertomorelosapp.puertomorelosapp.Register;

import com.google.firebase.auth.FirebaseAuth;
import com.puertomorelosapp.puertomorelosapp.Models.Register;
import com.puertomorelosapp.puertomorelosapp.Recover.IRecover_View;

/**
 * Created by rudielavilaperaza on 6/8/17.
 */

public interface IRegister_Presenter {

    void setView(IRegister_View view);

    void registerUser(Register register, FirebaseAuth auth);
}
