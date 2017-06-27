package com.puertomorelosapp.puertomorelosapp.Dagger;

import android.content.Context;

import com.puertomorelosapp.puertomorelosapp.Fragments.AboutUs.AboutUs_Presenter;
import com.puertomorelosapp.puertomorelosapp.Fragments.AboutUs.IAboutUs_Presenter;
import com.puertomorelosapp.puertomorelosapp.Fragments.Categories.Categories_Presenter;
import com.puertomorelosapp.puertomorelosapp.Fragments.Categories.ICategories_Presenter;
import com.puertomorelosapp.puertomorelosapp.Fragments.Map.IMap_Presenter;
import com.puertomorelosapp.puertomorelosapp.Fragments.Map.Map_Presenter;
import com.puertomorelosapp.puertomorelosapp.Fragments.Secundary_Main.ISecundaryMain_Presenter;
import com.puertomorelosapp.puertomorelosapp.Fragments.Secundary_Main.SecundaryMain_Presenter;
import com.puertomorelosapp.puertomorelosapp.Fragments.Thrid_Main.IThird_Main_Presenter;
import com.puertomorelosapp.puertomorelosapp.Fragments.Thrid_Main.Third_Main_Presenter;
import com.puertomorelosapp.puertomorelosapp.Login.ILogin_Presenter;
import com.puertomorelosapp.puertomorelosapp.Login.Login_Presenter;
import com.puertomorelosapp.puertomorelosapp.Main.IMain_Presenter;
import com.puertomorelosapp.puertomorelosapp.Main.Main_Presenter;
import com.puertomorelosapp.puertomorelosapp.Recover.IRecover_Presenter;
import com.puertomorelosapp.puertomorelosapp.Recover.Recover_Presenter;
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
    IRegister_Presenter provideRegisterPresenter(Context context) {
        return new Register_Presenter(context);
    }

    @Provides
    @Singleton
    IRecover_Presenter provideRecoverPresenter(Context context) {
        return new Recover_Presenter(context);
    }

    @Provides
    @Singleton
    IMain_Presenter provideMainPresenter(Context context) {
        return new Main_Presenter(context);
    }

    @Provides
    @Singleton
    IAboutUs_Presenter provideAboutUsPresenter(Context context) {
        return new AboutUs_Presenter(context);
    }

    @Provides
    @Singleton
    ICategories_Presenter provideCategoriesPresenter(Context context) {
        return new Categories_Presenter(context);
    }

    @Provides
    @Singleton
    IMap_Presenter provideMapPresenter(Context context) {
        return new Map_Presenter(context);
    }

    @Provides
    @Singleton
    ISecundaryMain_Presenter provideSecundaryPresenter(Context context) {
        return new SecundaryMain_Presenter(context);
    }

    @Provides
    @Singleton
    IThird_Main_Presenter provideThirdPresenter(Context context) {
        return new Third_Main_Presenter(context);
    }


}
