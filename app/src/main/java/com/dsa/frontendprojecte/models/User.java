package com.dsa.frontendprojecte.models;

import java.io.Serializable;

public class User implements Serializable {
    String name;
    String password;
    String mail;


    public User(String name, String psw, String mail) {
        this.name = name;
        this.password = psw;
        this.mail = mail;
    }

    public User(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPsw() {
        return password;
    }

    public void setPsw(String psw) {
        this.password = psw;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
