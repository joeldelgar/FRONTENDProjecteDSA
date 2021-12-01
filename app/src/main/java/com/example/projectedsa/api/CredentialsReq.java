package com.example.projectedsa.api;

public class CredentialsReq {
    private String name;
    private String password;

    public CredentialsReq(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
