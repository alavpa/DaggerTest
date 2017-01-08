package com.alavpa.dagger.presentation;

import android.app.Application;

import com.alavpa.dagger.presentation.di.application.ApplicationComponent;
import com.alavpa.dagger.presentation.di.application.ApplicationModule;
import com.alavpa.dagger.presentation.di.application.DaggerApplicationComponent;

/**
 * Created by alavpa on 7/01/17.
 */

public class DaggerApp extends Application {


    private ApplicationComponent applicationComponent;
    @Override
    public void onCreate() {
        super.onCreate();

        initInjector();
    }

    public void initInjector(){
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
