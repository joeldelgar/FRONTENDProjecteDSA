package com.dsa.frontendprojecte.connections;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.dsa.frontendprojecte.MainActivity;
import com.dsa.frontendprojecte.R;
import com.dsa.frontendprojecte.buyItemActivity;
import com.dsa.frontendprojecte.editUserActivity;
import com.dsa.frontendprojecte.models.*;
import com.dsa.frontendprojecte.storeActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UnityConnect {

/*    public Game game;
    public Inventory inventory;
    public Item item;
    public User user;*/
    public static String userName;

    public static void setUserName(String userName) {
        UnityConnect.userName = userName;
    }

    public void updateUserCoins(int coins) {
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(API.BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
        API gerritAPI = retrofit.create(API.class);
        Call<User> call = gerritAPI.updateUserCoins(userName, new CoinsCredentials(coins, userName));
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (!response.isSuccessful())
                    Log.i("onResponse", "updateUserCoins ERROR, call: " + response.code());
                else
                    Log.i("onResponse", "updateUserCoins OK");
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("onFailure", "updateUserCoins ERROR",t);
            }
        });
    }

    public void collectItem(String itemName) {
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(API.BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
        API gerritAPI = retrofit.create(API.class);
        Call<Inventory> call = gerritAPI.collectItem(itemName, new StoreCredentials(itemName, userName));
        call.enqueue(new Callback<Inventory>() {
            @Override
            public void onResponse(Call<Inventory> call, Response<Inventory> response) {
                if (!response.isSuccessful())
                    Log.i("onResponse", "collectItem ERROR, call: " + response.code());
                else
                    Log.i("onResponse", "collectItem OK");
            }
            @Override
            public void onFailure(Call<Inventory> call, Throwable t) {
                Log.e("onFailure", "collectItem ERROR",t);
            }
        });
    }

    public void saveGame(int points) {
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(API.BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
        API gerritAPI = retrofit.create(API.class);
        Call<Game> call = gerritAPI.addGame(new GameCredentials(userName, points));
        call.enqueue(new Callback<Game>() {
            @Override
            public void onResponse(Call<Game> call, Response<Game> response) {
                if (!response.isSuccessful() && (response.code() != 409)) {
                    Log.i("onResponse", "addGame ERROR, call: " + response.code());
                } else if (response.code() == 409) {
                    Gson gson2 = new GsonBuilder().setLenient().create();
                    Retrofit retrofit2 = new Retrofit.Builder().baseUrl(API.BASE_URL).addConverterFactory(GsonConverterFactory.create(gson2)).build();
                    API gerritAPI2 = retrofit2.create(API.class);
                    Call<Game> call2 = gerritAPI2.updateGame(userName, new GameCredentials(userName, points));
                    call.enqueue(new Callback<Game>() {
                        @Override
                        public void onResponse(Call<Game> call2, Response<Game> response) {
                            if (!response.isSuccessful()) {
                                Log.i("onResponse", "updateGame ERROR, call: " + response.code());

                            } else
                                Log.i("onResponse", "updateGame OK");
                        }
                        @Override
                        public void onFailure(Call<Game> call2, Throwable t) {
                            Log.e("onFailure", "updateGame ERROR",t);
                        }
                    });
                } else
                    Log.i("onResponse", "addGame OK");
            }
            @Override
            public void onFailure(Call<Game> call, Throwable t) {
                Log.e("onFailure", "addGame ERROR",t);
            }
        });
    }
}
