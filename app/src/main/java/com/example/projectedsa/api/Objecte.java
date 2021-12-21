package com.example.projectedsa.api;

import java.io.Serializable;

public class Objecte implements Serializable {
    public String name;
    public String description;
    public String points;
    public String avatar;

    public Objecte(String name, String description, String points, String avatar) {
        this.name = name;
        this.description = description;
        this.points = points;
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
