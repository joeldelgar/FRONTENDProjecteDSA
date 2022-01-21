package com.dsa.frontendprojecte.connections;

import android.util.Log;

import com.dsa.frontendprojecte.models.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

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

    public static String getUserName() {
        return userName;
    }

    public static String getUserInventory(){
        //String[] UserInventory;
        final String[] UserInventoryByCommas = new String[0];
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(API.BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
        API gerritAPI = retrofit.create(API.class);
        Call<List<Inventory>> call = gerritAPI.getUserInventory(userName);
        call.enqueue(new Callback<List<Inventory>>() {
            @Override
            public void onResponse(Call<List<Inventory>> call, Response<List<Inventory>> response) {
                Log.i("onResponse","getUserInventory ERROR, call: " + response.code());
                List<Inventory> inventoryList =  response.body();
                for (Inventory i: inventoryList) {
                    if (i.getItemName().equals("magicBerry"))
                        UserInventoryByCommas[0] = UserInventoryByCommas[0] + i.getItemQuantity() + ",";
                    else
                        UserInventoryByCommas[0] = UserInventoryByCommas[0] + i.getItemName() + ",";
                }
                //UserInventoryByCommas[0].deleteCharAt(UserInventoryByCommas[0].length() - 1);
                if(inventoryList == null){
                    Log.i("onResponse","getUserInventory OK, but Empty");
                } else {
                    Log.i("onResponse","getUserInventory OK");
                }
            }
            @Override
            public void onFailure(Call<List<Inventory>> call, Throwable t) {
                Log.e("onFailure", "getUserInventory ERROR",t);
                UserInventoryByCommas[0] = "-1";
            }
        });
        return UserInventoryByCommas[0];
    }

    public static User getUser() {
        final User[] user = new User[0];
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(API.BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
        API gerritAPI = retrofit.create(API.class);
        Call<User> call = gerritAPI.getUser(userName);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (!response.isSuccessful()) {
                    Log.i("onResponse", "getUser ERROR, call: " + response.code());
                    user[0] = response.body();
                } else {
                    Log.i("onResponse", "getUser OK");
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("onFailure", "getUser ERROR",t);
                user[0] = user[1];
            }
        });
        return user[0];
    }

    public static int getCoins() {
        User user = getUser();
        return user.getCoins();
    }

    public int updateCoins(int unityCoins) {
        final int[] finalCoins = new int[0];
        int databaseCoins = getCoins();

        if (unityCoins != databaseCoins + 1) {
            finalCoins[0] = databaseCoins + 1;
            Gson gson = new GsonBuilder().setLenient().create();
            Retrofit retrofit = new Retrofit.Builder().baseUrl(API.BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
            API gerritAPI = retrofit.create(API.class);
            Call<User> call = gerritAPI.updateUserCoins(userName, new CoinsCredentials(finalCoins[0], userName));
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (!response.isSuccessful()) {
                        Log.i("onResponse", "updateUserCoins ERROR, call: " + response.code());
                        finalCoins[0] = -1;
                    } else
                        Log.i("onResponse", "updateUserCoins OK");
                }
                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Log.e("onFailure", "updateUserCoins ERROR", t);
                    finalCoins[0] = -1;
                }
            });
        } else {
            Log.i("onResponse", "updateUserCoins OK");
            finalCoins[0] = unityCoins;
        }
        return finalCoins[0];
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

    public void useItem(String itemName) {
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(API.BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
        API gerritAPI = retrofit.create(API.class);
        Call<Inventory> call = gerritAPI.useItem(itemName, new StoreCredentials(itemName, userName));
        call.enqueue(new Callback<Inventory>() {
            @Override
            public void onResponse(Call<Inventory> call, Response<Inventory> response) {
                if (!response.isSuccessful()) {
                    Log.i("onResponse", "useItem ERROR, call: " + response.code());
                } else
                    Log.i("onResponse", "useItem OK");
            }
            @Override
            public void onFailure(Call<Inventory> call, Throwable t) {
                Log.e("onFailure", "useItem ERROR", t);
            }
        });
    }

    public static Game getGame() {
        final Game[] game = new Game[0];
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(API.BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
        API gerritAPI = retrofit.create(API.class);
        Call<Game> call = gerritAPI.getGame(userName);
        call.enqueue(new Callback<Game>() {
            @Override
            public void onResponse(Call<Game> call, Response<Game> response) {
                if (!response.isSuccessful()) {
                    Log.i("onResponse", "getGame ERROR, call: " + response.code());
                    game[0] = response.body();
                } else {
                    Log.i("onResponse", "getGame OK");
                }
            }
            @Override
            public void onFailure(Call<Game> call, Throwable t) {
                Log.e("onFailure", "getGame ERROR",t);
                game[0] = game[1];
            }
        });
        return game[0];
    }

    public static int getPoints() {
        Game game = getGame();
        return game.getPoints();
    }

    public static int getHealth() {
        Game game = getGame();
        return game.getHealth();
    }

    public void saveGame(int points, int health) {
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(API.BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
        API gerritAPI = retrofit.create(API.class);
        Call<Game> call = gerritAPI.addGame(new GameCredentials(userName, points, health));
        call.enqueue(new Callback<Game>() {
            @Override
            public void onResponse(Call<Game> call, Response<Game> response) {
                if (!response.isSuccessful() && (response.code() != 409)) {
                    Log.i("onResponse", "addGame ERROR, call: " + response.code());
                } else if (response.code() == 409) {
                    Gson gson2 = new GsonBuilder().setLenient().create();
                    Retrofit retrofit2 = new Retrofit.Builder().baseUrl(API.BASE_URL).addConverterFactory(GsonConverterFactory.create(gson2)).build();
                    API gerritAPI2 = retrofit2.create(API.class);
                    Call<Game> call2 = gerritAPI2.updateGame(userName, new GameCredentials(userName, points, health));
                    call2.enqueue(new Callback<Game>() {
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
