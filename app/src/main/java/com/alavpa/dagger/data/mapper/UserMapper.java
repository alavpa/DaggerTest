package com.alavpa.dagger.data.mapper;

import com.alavpa.dagger.data.model.UserResponse;
import com.alavpa.dagger.domain.model.User;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by alavpa on 7/01/17.
 */

@Singleton
public class UserMapper {

    @Inject
    public UserMapper(){}

    public User map(UserResponse userResponse){
        User user = new User();
        user.setId(userResponse.getId());
        user.setName(userResponse.getName());
        user.setPhone(userResponse.getPhone());
        return user;
    }
}
