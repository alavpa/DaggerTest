package com.alavpa.dagger.presentation.di.activity;

import android.app.Activity;

import com.alavpa.dagger.presentation.di.application.ApplicationComponent;
import com.alavpa.dagger.presentation.di.PerActivity;

import dagger.Component;

/**
 * Created by alavpa on 7/01/17.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    Activity activity();
}
