package com.alavpa.dagger.data.repository.datastore.disk;

import com.alavpa.dagger.data.model.UserResponse;
import com.alavpa.dagger.data.repository.datastore.UserDataStore;
import com.alavpa.dagger.data.repository.datastore.utils.FileManager;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

/**
 * Created by alavpa on 6/01/17.
 */

@Singleton
public class DiskDataStore implements UserDataStore {

    FileManager fileManager;
    @Inject
    public DiskDataStore(FileManager fileManager){
        this.fileManager = fileManager;
    }

    @Override
    public Single<List<UserResponse>> getUsers() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Single<UserResponse> getUser(final long id) {

        return Single.fromCallable(new Callable<UserResponse>() {
            @Override
            public UserResponse call() throws Exception {
                return fileManager.readFile(id);
            }
        });
    }
}
