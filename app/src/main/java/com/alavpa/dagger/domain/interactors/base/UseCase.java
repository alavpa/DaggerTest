package com.alavpa.dagger.domain.interactors.base;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by alavpa on 7/01/17.
 */

public abstract class UseCase<T> {

    private
    CompositeDisposable compositeDisposable;

    public UseCase(){
        compositeDisposable = new CompositeDisposable();
    }

    public abstract Single<T> buildUseCase(Object... params);

    public void execute(DisposableSingleObserver<T> observer,Object... params){
        buildUseCase(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);

        compositeDisposable.add(observer);
    }

    private void addDisposable(Disposable disposable){
        compositeDisposable.add(disposable);
    }

    public void dispose(){
        if(!compositeDisposable.isDisposed()){
            compositeDisposable.dispose();
        }
    }
}
