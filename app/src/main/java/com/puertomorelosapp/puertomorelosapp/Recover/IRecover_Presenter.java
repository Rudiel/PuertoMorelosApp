package com.puertomorelosapp.puertomorelosapp.Recover;

import com.google.firebase.auth.FirebaseAuth;
import com.puertomorelosapp.puertomorelosapp.Models.Recovery;

/**
 * Created by rudielavilaperaza on 6/8/17.
 */

public interface IRecover_Presenter {

    void setView(IRecover_View view);

    void recoveryAccount(Recovery recovery, FirebaseAuth auth);
}
