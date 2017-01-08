package com.alavpa.dagger.data.repository.datastore.utils;

import android.content.Context;
import android.support.annotation.NonNull;

import com.alavpa.dagger.data.model.UserResponse;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.Callable;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

/**
 * Created by alavpa on 7/01/17.
 */

@Singleton
public class FileManager {

    private static final int BUFFER_SIZE=8192;
    Context context;
    Gson gson;

    @Inject
    public FileManager(Context context){
        this.context = context;
        gson = new Gson();
    }

    public UserResponse readFile(long id) throws Exception {
        File file = getFile(id);

        String content = readFileContent(file);

        try {
            return gson.fromJson(content, UserResponse.class);
        }catch (Throwable e){
            throw new Exception("Error reading from gson");
        }
    }

    @NonNull
    private File getFile(long id) {
        File dir = context.getFilesDir();
        return new File(dir,String.valueOf(id));
    }

    public void writeFile(UserResponse userResponse) throws Exception {
        File file = getFile(userResponse.getId());
        String content = gson.toJson(userResponse,UserResponse.class);
        writeFileContent(file,content);
    }

    private void writeFileContent(File file, String fileContent) throws Exception {
        if (!file.exists()) {
            try {
                FileWriter writer = new FileWriter(file);
                writer.write(fileContent);
                writer.close();
            } catch (Throwable e) {
                throw new Exception("Error writting file.");
            }
        }
    }

    private String readFileContent(File file) throws Exception {
        StringBuffer fileContentBuilder = new StringBuffer();
        if (file.exists()) {
            int size;
            try {
                FileInputStream fileReader = new FileInputStream(file);
                byte[] buffer = new byte[BUFFER_SIZE];
                while ((size = fileReader.read(buffer)) > -1) {
                    fileContentBuilder.append(new String(buffer,0,size));
                }
                fileReader.close();
            } catch (Throwable e) {
                throw new Exception("Error reading file.");
            }
        }

        return fileContentBuilder.toString();
    }

    public boolean exists(long id){
        return getFile(id).exists();
    }
}
