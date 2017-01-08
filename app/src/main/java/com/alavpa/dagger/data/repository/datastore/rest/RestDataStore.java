package com.alavpa.dagger.data.repository.datastore.rest;

import com.alavpa.dagger.data.model.UserResponse;
import com.alavpa.dagger.data.repository.datastore.UserDataStore;
import com.alavpa.dagger.data.repository.datastore.utils.FileManager;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;
import io.reactivex.functions.Consumer;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by alavpa on 7/01/17.
 */

@Singleton
public class RestDataStore implements UserDataStore {

    RestApi restApi;
    FileManager fileManager;

    @Inject
    public RestDataStore(FileManager fileManager){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        restApi = retrofit.create(RestApi.class);

        this.fileManager = fileManager;
    }

    @Override
    public Single<List<UserResponse>> getUsers() {
        return restApi.users();
    }

    @Override
    public Single<UserResponse> getUser(long id) {
        return restApi.user(id)
                .doOnSuccess(new Consumer<UserResponse>() {
                    @Override
                    public void accept(UserResponse userResponse) throws Exception {
                        fileManager.writeFile(userResponse);
                    }
                });
    }
}
