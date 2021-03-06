package com.dsa.frontendprojecte.models;

import java.io.Serializable;

public class Item implements Serializable {
    public String name;
    public String description;
    public int cost;
    public String avatar;

    public Item(String name, String description, int cost, String avatar) {
        this.name = name;
        this.description = description;
        this.cost = cost;
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

    public String getCost() {
        return String.valueOf(cost);
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
