package com.dsa.frontendprojecte.connections;

import androidx.annotation.NonNull;

import com.dsa.frontendprojecte.models.User;

public class onResponseReturnImpl implements onResponseReturn {
    public void onSuccessString(@NonNull String value) {

    }
    public void onSuccessInt(@NonNull int value) {
        UnityConnect.setCoins(value);
    }
    public void onSuccessUser(@NonNull User value){
        UnityConnect.setUser(value);
    }
    public void onError(@NonNull Throwable throwable){

    }
}