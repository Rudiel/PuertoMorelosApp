package com.puertomorelosapp.puertomorelosapp.Utils;

import android.app.Application;

import com.onesignal.OneSignal;
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

        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .init();
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
