package com.dsa.frontendprojecte.models;

public class CoinsCredentials {
    private int coins;
    private String userName;

    public CoinsCredentials(int coins, String userName) {
        this.coins = coins;
        this.userName = userName;
    }

    public int getUserCoins() {
        return coins;
    }

    public void setUserCoins(String itemName) {
        this.coins = coins;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
