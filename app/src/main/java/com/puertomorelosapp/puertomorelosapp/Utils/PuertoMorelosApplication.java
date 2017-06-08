package com.puertomorelosapp.puertomorelosapp.Utils;

import android.app.Application;
import android.content.Context;

import com.puertomorelosapp.puertomorelosapp.Dagger.AppComponent;
import com.puertomorelosapp.puertomorelosapp.Dagger.AppModule;
import com.puertomorelosapp.puertomorelosapp.Dagger.DaggerAppComponent;

/**
 * Created by rudielavilaperaza on 6/8/17.
 */

public class PuertoMorelosApplication extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = initDagger(this);
    }

    protected AppComponent initDagger(PuertoMorelosApplication application) {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(application))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
