package com.example.projectedsa.api;

public class GameCredentials {
    String userName;
    int points;

    public GameCredentials(){}

    public GameCredentials(String userName, int points) {
        this.userName = userName;
        this.points = points;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
