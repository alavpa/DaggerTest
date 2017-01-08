package com.alavpa.dagger.domain.repository;

import com.alavpa.dagger.domain.model.User;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by alavpa on 6/01/17.
 */

public interface UserRepository {

    Single<List<User>> getUsers();
    Single<User> getUser(long id);
}
