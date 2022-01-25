package com.dsa.frontendprojecte.connections;

import android.os.StrictMode;
import android.util.Log;

import androidx.annotation.Nullable;

import com.dsa.frontendprojecte.launcherActivity;
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

    public static onResponseReturn oRR;

    public static User user;
    public static Game game;

    public static String userName;
    public static String UserInventoryByCommas;

    public static int coins;

    public static void setCoins(int coins) {
        UnityConnect.coins = coins;
    }

    public static int finalCoins;

    public static void setUserName(String userName) {
        UnityConnect.userName = userName;
    }

    public static String getUserName() {
        return userName;
    }

    public static void setUser(User user) {
        UnityConnect.user = user;
    }

    public static String getUserInventory(){
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(API.BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
        API gerritAPI = retrofit.create(API.class);
        Call<List<Inventory>> call = gerritAPI.getUserInventory(userName);
        call.enqueue(new Callback<List<Inventory>>() {
            @Override
            public void onResponse(Call<List<Inventory>> call, Response<List<Inventory>> response) {
                if (!response.isSuccessful()) {
                    Log.i("onResponse","getUserInventory ERROR, call: " + response.code());
                } else {
                    List<Inventory> inventoryList =  response.body();
                    if(inventoryList == null){
                        Log.i("onResponse","getUserInventory OK, but Empty");
                    } else {
                        Log.i("onResponse","getUserInventory OK");
                        for (Inventory i: inventoryList) {
                            if (i.getItemName().equals("magicBerry"))
                                UserInventoryByCommas = UserInventoryByCommas + i.getItemQuantity() + ",";
                            else
                                UserInventoryByCommas = UserInventoryByCommas + i.getItemName() + ",";
                        }
                        //UserInventoryByCommas.deleteCharAt(UserInventoryByCommas.length() - 1);
                    }
                }
            }
            @Override
            public void onFailure(Call<List<Inventory>> call, Throwable t) {
                Log.e("onFailure", "getUserInventory ERROR",t);
                UserInventoryByCommas = "-1";
            }
        });
        return UserInventoryByCommas;
    }

    public static void getUser(@Nullable onResponseReturn callbacks) {
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(API.BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
        API gerritAPI = retrofit.create(API.class);
        Call<User> call = gerritAPI.getUser(userName);
        Log.i("Testing", "getUser 1");
/*        try {
            Log.i("Testing", "getUser 2");
            Response<User> response = call.execute();
            Log.i("Testing", "getUser 3");
            if (!response.isSuccessful()) {
                Log.i("call", "getUser ERROR, call: " + response.code());
            } else {
                Log.i("call", "getUser OK");
                user2 = response.body();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }*/
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (!response.isSuccessful()) {
                    Log.i("onResponse", "getUser ERROR, call: " + response.code());
                } else {
                    Log.i("onResponse", "getUser OK");
                    User user2 = response.body();
                    Log.i("onResponse", "getUserCoins: " + user2.getCoins());
                    int coins2 = user2.getCoins();
                    //callbacks.onSuccessUser(user2);
                    callbacks.onSuccessInt(coins2);
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("onFailure", "getUser ERROR",t);
            }
        });
//        return user2;
    }

    public static int getCoins() {
        getUser(oRR);
        user = null;
//        User user = oRR;
//        return getUser().getCoins();
//        Log.i("getCoins", "getUserCoins: " + user.getCoins());
//        return user.getCoins();
        return coins;
    }

/*    public int updateCoins(int unityCoins) { ;
        int databaseCoins = getCoins();
        if (unityCoins != databaseCoins + 1) {
            finalCoins = databaseCoins + 1;
            Gson gson = new GsonBuilder().setLenient().create();
            Retrofit retrofit = new Retrofit.Builder().baseUrl(API.BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
            API gerritAPI = retrofit.create(API.class);
            Call<User> call = gerritAPI.updateUserCoins(userName, new CoinsCredentials(finalCoins, userName));
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (!response.isSuccessful()) {
                        Log.i("onResponse", "updateUserCoins ERROR, call: " + response.code());
                        finalCoins = -1;
                    } else
                        Log.i("onResponse", "updateUserCoins OK");
                }
                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Log.e("onFailure", "updateUserCoins ERROR", t);
                    finalCoins = -1;
                }
            });
        } else {
            Log.i("onResponse", "updateUserCoins OK");
            finalCoins = unityCoins;
        }
        return finalCoins;
    }*/

    public static void collectItem(String itemName) {
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

    public static void useItem(String itemName) {
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
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(API.BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
        API gerritAPI = retrofit.create(API.class);
        Call<Game> call = gerritAPI.getGame(userName);
        call.enqueue(new Callback<Game>() {
            @Override
            public void onResponse(Call<Game> call, Response<Game> response) {
                if (!response.isSuccessful()) {
                    Log.i("onResponse", "getGame ERROR, call: " + response.code());
                } else {
                    Log.i("onResponse", "getGame OK");
                    game = response.body();
                }
            }
            @Override
            public void onFailure(Call<Game> call, Throwable t) {
                Log.e("onFailure", "getGame ERROR",t);
            }
        });
        return game;
    }

    public static int getPoints() {
        return getGame().getPoints();
    }

    public static int getHealth() {
        return getGame().getHealth();
    }

    public static void saveGame(int points, int health) {
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
