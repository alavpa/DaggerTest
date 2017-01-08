package com.alavpa.dagger.data.repository.datastore.rest;

import com.alavpa.dagger.data.model.UserResponse;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by alavpa on 7/01/17.
 */

public interface RestApi {

    @GET("/users/{id}")
    Single<UserResponse> user(@Path("id") long id);

    @GET("/users")
    Single<List<UserResponse>> users();
}
