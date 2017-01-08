package com.alavpa.dagger.presentation.di.application;

import android.content.Context;

import com.alavpa.dagger.domain.repository.UserRepository;
import com.alavpa.dagger.data.repository.UserDataRepository;
import com.alavpa.dagger.presentation.DaggerApp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by alavpa on 7/01/17.
 */

@Module
public class ApplicationModule {

    DaggerApp daggerApp;

    public ApplicationModule(DaggerApp daggerApp){
        this.daggerApp = daggerApp;
    }

    @Provides @Singleton
    UserRepository provideUserRepository(UserDataRepository userDataRepository){
        return userDataRepository;
    }

    @Provides @Singleton
    Context provideContext(){
        return daggerApp;
    }
}
