package com.dsa.frontendprojecte.models;

import java.io.Serializable;

public class User implements Serializable {
    String name;
    String password;
    String mail;
    int coins;

    public User(String name, String psw, String mail, int coins) {
        this.name = name;
        this.password = psw;
        this.mail = mail;
        this.coins = coins;
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

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }
}
