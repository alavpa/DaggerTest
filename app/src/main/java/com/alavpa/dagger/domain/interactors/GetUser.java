package com.alavpa.dagger.domain.interactors;

import com.alavpa.dagger.domain.interactors.base.UseCase;
import com.alavpa.dagger.domain.repository.UserRepository;
import com.alavpa.dagger.domain.model.User;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Created by alavpa on 6/01/17.
 */

public class GetUser extends UseCase<User>{

    UserRepository userRepository;

    @Inject
    public GetUser(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public Single<User> buildUseCase(Object... params) {
        return userRepository.getUser((long)params[0]);
    }
}
