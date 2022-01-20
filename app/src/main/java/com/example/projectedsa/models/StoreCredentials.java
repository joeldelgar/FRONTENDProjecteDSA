package com.example.projectedsa.models;

public class StoreCredentials {
    private String itemName;
    private String userName;

    public StoreCredentials(String itemName, String userName) {
        this.itemName = itemName;
        this.userName = userName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
