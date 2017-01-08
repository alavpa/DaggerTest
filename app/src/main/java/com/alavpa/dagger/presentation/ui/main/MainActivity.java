package com.alavpa.dagger.presentation.ui.main;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.alavpa.dagger.R;
import com.alavpa.dagger.presentation.base.BaseActivity;
import com.alavpa.dagger.presentation.di.user.DaggerUserComponent;
import com.alavpa.dagger.domain.model.User;
import com.alavpa.dagger.presentation.di.user.UserModule;
import com.alavpa.dagger.presentation.ui.main.adapter.UsersAdapter;
import com.alavpa.dagger.presentation.navigation.Navigator;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends BaseActivity implements MainView {

    @Inject
    Navigator navigator;

    @Inject
    MainPresenter mainPresenter;

    RecyclerView rv_users;
    UsersAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initInjector();

        mainPresenter.attachView(this);

    }

    private void initInjector() {
        DaggerUserComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .userModule(new UserModule())
                .build()
                .inject(this);
    }

    private void initViews(){
        rv_users = (RecyclerView) findViewById(R.id.rv_users);
    }

    @Override
    protected void onResume() {
        super.onResume();

        mainPresenter.loadUsers();
    }

    @Override
    public void inflateUsers(List<User> users) {
        if(adapter==null){

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

            adapter = new UsersAdapter(this);

            adapter.setOnClickListener(new UsersAdapter.OnClickListener() {
                @Override
                public void onClick(User user) {
                    mainPresenter.onSelectUser(user);
                }
            });

            rv_users.setLayoutManager(linearLayoutManager);
            rv_users.setAdapter(adapter);

        }

        adapter.setUsers(users);

    }

    @Override
    public void goToDetails(long id) {
        navigator.navigateToDetails(this,id);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mainPresenter.dispose();
        mainPresenter.detachView();
    }
}
