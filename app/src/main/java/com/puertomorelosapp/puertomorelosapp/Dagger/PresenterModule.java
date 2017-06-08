package com.puertomorelosapp.puertomorelosapp.Dagger;

import android.content.Context;

import com.puertomorelosapp.puertomorelosapp.Login.ILogin_Presenter;
import com.puertomorelosapp.puertomorelosapp.Login.Login_Presenter;
import com.puertomorelosapp.puertomorelosapp.Register.IRegister_Presenter;
import com.puertomorelosapp.puertomorelosapp.Register.Register_Presenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by rudielavilaperaza on 6/8/17.
 */
@Module
public class PresenterModule {

    @Provides
    @Singleton
    ILogin_Presenter provideLoginPresenter(Context context) {
        return new Login_Presenter(context);
    }

    @Provides
    @Singleton
    IRegister_Presenter provideRegisterPresenter(Context context){
        return new Register_Presenter(context);
    }

}
