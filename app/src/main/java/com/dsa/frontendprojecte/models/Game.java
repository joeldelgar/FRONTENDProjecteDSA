package com.dsa.frontendprojecte.models;

public class Game {
    String userName;
    int points;
    int health;

    public Game(){}

    public Game(String userName, int points, int health) {
        this.userName = userName;
        this.points = points;
        this.health = health;
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

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
