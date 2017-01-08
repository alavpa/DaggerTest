package com.alavpa.dagger.presentation.base;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.alavpa.dagger.R;
import com.alavpa.dagger.presentation.DaggerApp;
import com.alavpa.dagger.presentation.di.application.ApplicationComponent;
import com.alavpa.dagger.presentation.di.activity.ActivityModule;

/**
 * Created by alavpa on 7/01/17.
 */

public class BaseActivity extends AppCompatActivity implements BaseView {

    ProgressDialog progressDialog;
    AlertDialog alertDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getApplicationComponent().inject(this);
    }

    protected ApplicationComponent getApplicationComponent() {
        return ((DaggerApp)getApplication()).getApplicationComponent();
    }

    protected ActivityModule getActivityModule(){
        return new ActivityModule(this);
    }

    @Override
    public void showError(String message) {
        if(alertDialog==null){
            alertDialog = new AlertDialog.Builder(this)
                    .setTitle(android.R.string.dialog_alert_title)
                    .setNeutralButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create();
        }

        alertDialog.setMessage(message);
        alertDialog.show();
    }

    @Override
    public void showLoading() {

        if(progressDialog == null){
            progressDialog = new ProgressDialog(this);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage(getString(R.string.loading));
        }

        progressDialog.show();
    }

    @Override
    public void hideLoading() {
        progressDialog.dismiss();
        progressDialog = null;
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        alertDialog = null;
        progressDialog = null;
    }
}
