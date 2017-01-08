package com.alavpa.dagger.data.repository;

import com.alavpa.dagger.data.mapper.UserMapper;
import com.alavpa.dagger.data.model.UserResponse;
import com.alavpa.dagger.data.repository.datastore.UserDataStore;
import com.alavpa.dagger.data.repository.datastore.UserDataStoreFactory;
import com.alavpa.dagger.domain.repository.UserRepository;
import com.alavpa.dagger.domain.model.User;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;

/**
 * Created by alavpa on 6/01/17.
 */

@Singleton
public class UserDataRepository implements UserRepository{

    UserDataStoreFactory userDataStoreFactory;
    UserMapper userMapper;

    @Inject
    public UserDataRepository(UserDataStoreFactory userDataStoreFactory, UserMapper userMapper){
        this.userDataStoreFactory = userDataStoreFactory;
        this.userMapper = userMapper;
    }

    @Override
    public Single<List<User>> getUsers() {

        return userDataStoreFactory.getRestDataStore().getUsers()
                .flatMap(new Function<List<UserResponse>, SingleSource<List<User>>>() {
                    @Override
                    public SingleSource<List<User>> apply(List<UserResponse> userResponses) throws Exception {
                        return Observable.fromIterable(userResponses)
                                .flatMap(new Function<UserResponse, ObservableSource<User>>() {
                                    @Override
                                    public ObservableSource<User> apply(UserResponse userResponse) throws Exception {
                                        User user = userMapper.map(userResponse);
                                        return Observable.just(user);
                                    }
                                })
                                .toList();
                    }
                });
    }

    @Override
    public Single<User> getUser(long id) {

        if(userDataStoreFactory.isCached(id)) {
            return userDataStoreFactory.getDiskDataStore().getUser(id)
                    .map(new Function<UserResponse, User>() {
                        @Override
                        public User apply(UserResponse userResponse) throws Exception {
                            return userMapper.map(userResponse);
                        }
                    });
        }else{
            return userDataStoreFactory.getRestDataStore().getUser(id)
                    .map(new Function<UserResponse, User>() {
                        @Override
                        public User apply(UserResponse userResponse) throws Exception {
                            return userMapper.map(userResponse);
                        }
                    });
        }
    }
}
