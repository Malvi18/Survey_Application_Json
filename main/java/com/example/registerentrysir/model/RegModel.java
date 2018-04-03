package com.example.registerentrysir.model;

import java.io.Serializable;

/**
 * Created by Dell on 05-01-2018.
 */

public class RegModel implements Serializable {
    private int id;
    private String username, password;

    public RegModel() {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
