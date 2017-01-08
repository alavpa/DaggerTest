package com.alavpa.dagger.presentation.ui.main;

import com.alavpa.dagger.domain.interactors.GetUsers;
import com.alavpa.dagger.presentation.di.PerActivity;
import com.alavpa.dagger.domain.model.User;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.DisposableSingleObserver;

/**
 * Created by alavpa on 7/01/17.
 */

@PerActivity
public class MainPresenter {

    MainView mainView;
    GetUsers getUsers;

    @Inject
    public MainPresenter(GetUsers getUsers) {
        this.getUsers = getUsers;
    }

    public void attachView(MainView mainView) {
        this.mainView = mainView;
    }

    public void loadUsers() {

        mainView.showLoading();

        getUsers.execute(new DisposableSingleObserver<List<User>>() {
            @Override
            public void onSuccess(List<User> users) {
                mainView.hideLoading();
                mainView.inflateUsers(users);
            }

            @Override
            public void onError(Throwable e) {
                mainView.hideLoading();
                mainView.showError(e.getMessage());
            }
        });

    }

    public void detachView(){
        mainView = null;
    }

    public void dispose(){
        getUsers.dispose();
    }

    public void onSelectUser(User user) {
        mainView.goToDetails(user.getId());
    }
}
