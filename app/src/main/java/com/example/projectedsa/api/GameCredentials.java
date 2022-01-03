package com.example.projectedsa.api;

public class GameCredentials {
    String userName;
    int coins;
    int points;

    public GameCredentials(){}

    public GameCredentials(String userName, int coints, int points) {
        this.userName = userName;
        this.coins = coints;
        this.points = points;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coints) {
        this.coins = coints;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
