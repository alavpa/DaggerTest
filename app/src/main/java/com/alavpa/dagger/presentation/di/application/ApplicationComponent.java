package com.alavpa.dagger.presentation.di.application;

import android.content.Context;

import com.alavpa.dagger.presentation.base.BaseActivity;
import com.alavpa.dagger.domain.repository.UserRepository;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by alavpa on 7/01/17.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(BaseActivity baseActivity);

    //share dependencies with dependant components

    UserRepository userRepository();
    Context context();
}
