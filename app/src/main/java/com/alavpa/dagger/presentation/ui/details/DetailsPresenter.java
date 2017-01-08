package com.alavpa.dagger.presentation.ui.details;

import com.alavpa.dagger.presentation.di.PerActivity;
import com.alavpa.dagger.domain.interactors.GetUser;
import com.alavpa.dagger.domain.model.User;

import javax.inject.Inject;

import io.reactivex.observers.DisposableSingleObserver;

/**
 * Created by alavpa on 7/01/17.
 */

@PerActivity
public class DetailsPresenter {

    DetailsView detailsView;
    GetUser getUser;

    @Inject
    public DetailsPresenter(GetUser getUser){
        this.getUser = getUser;
    }

    public void loadUser(long id){
        detailsView.showLoading();
        getUser.execute(new DisposableSingleObserver<User>() {
            @Override
            public void onSuccess(User user) {
                detailsView.hideLoading();
                detailsView.inflateUser(user);
            }

            @Override
            public void onError(Throwable e) {
                detailsView.hideLoading();
                detailsView.showError(e.getMessage());
            }
        },id);
    }

    public void attachView(DetailsView detailsView){
        this.detailsView = detailsView;
    }

    public void detachView(){
        detailsView = null;
    }

    public void dispose(){
        getUser.dispose();
    }
}
