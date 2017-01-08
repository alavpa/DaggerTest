package com.alavpa.dagger.domain.model;

/**
 * Created by alavpa on 6/01/17.
 */

public class User {

    private
    long id;

    private
    String name;

    private
    String phone;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
