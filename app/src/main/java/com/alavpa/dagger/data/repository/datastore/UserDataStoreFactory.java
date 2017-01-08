package com.alavpa.dagger.data.repository.datastore;

import com.alavpa.dagger.data.repository.datastore.disk.DiskDataStore;
import com.alavpa.dagger.data.repository.datastore.utils.FileManager;
import com.alavpa.dagger.data.repository.datastore.rest.RestDataStore;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by alavpa on 7/01/17.
 */

@Singleton
public class UserDataStoreFactory {

    FileManager fileManager;

    @Inject
    public UserDataStoreFactory(FileManager fileManager){
        this.fileManager = fileManager;
    }

    public UserDataStore getDiskDataStore(){
        return new DiskDataStore(fileManager);
    }

    public UserDataStore getRestDataStore(){
        return new RestDataStore(fileManager);
    }

    public boolean isCached(long id){
        return fileManager.exists(id);
    }
}
