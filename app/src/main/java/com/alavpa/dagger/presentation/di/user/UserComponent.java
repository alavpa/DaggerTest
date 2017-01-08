package com.alavpa.dagger.presentation.di.user;

import com.alavpa.dagger.presentation.di.application.ApplicationComponent;
import com.alavpa.dagger.presentation.di.PerActivity;
import com.alavpa.dagger.presentation.di.activity.ActivityComponent;
import com.alavpa.dagger.presentation.di.activity.ActivityModule;
import com.alavpa.dagger.presentation.ui.details.DetailsActivity;
import com.alavpa.dagger.presentation.ui.main.MainActivity;

import dagger.Component;

/**
 * Created by alavpa on 7/01/17.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {UserModule.class, ActivityModule.class})
public interface UserComponent extends ActivityComponent{

    void inject(MainActivity mainActivity);
    void inject(DetailsActivity detailsActivity);
}
