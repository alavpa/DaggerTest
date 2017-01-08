package com.alavpa.dagger.data.repository.datastore;

import com.alavpa.dagger.data.model.UserResponse;
import com.alavpa.dagger.domain.model.User;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by alavpa on 6/01/17.
 */

public interface UserDataStore {

    Single<List<UserResponse>> getUsers();

    Single<UserResponse> getUser(long id);
}
