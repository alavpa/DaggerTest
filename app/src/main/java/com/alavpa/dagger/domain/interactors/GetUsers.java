package com.alavpa.dagger.domain.interactors;

import com.alavpa.dagger.domain.interactors.base.UseCase;
import com.alavpa.dagger.domain.repository.UserRepository;
import com.alavpa.dagger.domain.model.User;

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Created by alavpa on 6/01/17.
 */

public class GetUsers extends UseCase<List<User>>{

    UserRepository userRepository;

    @Inject
    public GetUsers(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public Single<List<User>> buildUseCase(Object... params) {
        return userRepository.getUsers();
    }
}
