package com.alavpa.dagger.presentation.ui.details;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.alavpa.dagger.R;
import com.alavpa.dagger.presentation.di.user.DaggerUserComponent;
import com.alavpa.dagger.domain.model.User;
import com.alavpa.dagger.presentation.base.BaseActivity;
import com.alavpa.dagger.presentation.di.user.UserModule;
import com.alavpa.dagger.presentation.navigation.Navigator;

import javax.inject.Inject;

/**
 * Created by alavpa on 7/01/17.
 */

public class DetailsActivity extends BaseActivity implements DetailsView {

    @Inject
    DetailsPresenter presenter;

    TextView tv_name;
    TextView tv_phone;

    long id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        if(savedInstanceState==null) {
            id = getIntent().getLongExtra(Navigator.EXTRA_ID, -1);
        }else {
            id = savedInstanceState.getLong(Navigator.EXTRA_ID);
        }

        initInjectors();
        initViews();

        presenter.attachView(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putLong(Navigator.EXTRA_ID,id);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onResume() {
        super.onResume();

        //tv_name.setText(String.valueOf(id));
        presenter.loadUser(id);
    }

    public void initInjectors(){
        DaggerUserComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .userModule(new UserModule())
                .build()
                .inject(this);
    }

    public void initViews(){

        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_phone = (TextView) findViewById(R.id.tv_phone);
    }

    @Override
    public void inflateUser(User user) {
        tv_name.setText(user.getName());
        tv_phone.setText(user.getPhone());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.dispose();
        presenter.detachView();
    }
}
