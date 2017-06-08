package com.puertomorelosapp.puertomorelosapp.Dagger;

import com.puertomorelosapp.puertomorelosapp.Login.Login_Activity;
import com.puertomorelosapp.puertomorelosapp.Login.Login_Presenter;
import com.puertomorelosapp.puertomorelosapp.Register.Register_Activity;
import com.puertomorelosapp.puertomorelosapp.Register.Register_Presenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by rudielavilaperaza on 6/8/17.
 */
@Singleton
@Component(modules = {AppModule.class, PresenterModule.class})
public interface AppComponent {

    //Login
    void inject(Login_Activity activity);

    void inject(Login_Presenter target);

    //Register

    void inject(Register_Activity activity);

    void inject(Register_Presenter target);

}
