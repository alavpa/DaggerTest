package com.alavpa.dagger.presentation.di.activity;

import android.app.Activity;

import com.alavpa.dagger.presentation.di.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by alavpa on 7/01/17.
 */
@Module
public class ActivityModule {

    private
    final Activity activity;

    public ActivityModule(Activity activity){
        this.activity = activity;
    }

    @Provides @PerActivity
    public Activity providesActivity(){
        return this.activity;
    }
}
