package com.alavpa.dagger.presentation.navigation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.alavpa.dagger.presentation.di.PerActivity;
import com.alavpa.dagger.presentation.ui.details.DetailsActivity;
import com.alavpa.dagger.presentation.ui.empty.EmptyActivity;
import com.alavpa.dagger.presentation.ui.main.MainActivity;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by alavpa on 7/01/17.
 */

@PerActivity
public class Navigator {

    public static final String EXTRA_ID = "EXTRA_ID";

    @Inject
    public Navigator(){

    }

    private Intent getIntent(Context context,Class<? extends Activity> activityClass){
        return new Intent(context,activityClass);
    }

    public void navigateToMain(Context context){
        Intent intent = getIntent(context, MainActivity.class);
        context.startActivity(intent);
    }

    public void navigateToEmpty(Context context){
        Intent intent = getIntent(context, EmptyActivity.class);
        context.startActivity(intent);
    }

    public void navigateToDetails(Context context, long id){
        Intent intent = getIntent(context, DetailsActivity.class);
        intent.putExtra(EXTRA_ID,id);
        context.startActivity(intent);
    }
}
