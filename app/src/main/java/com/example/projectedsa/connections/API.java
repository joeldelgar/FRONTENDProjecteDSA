package com.example.projectedsa.connections;

import com.example.projectedsa.models.CoinsCredentials;
import com.example.projectedsa.models.Game;
import com.example.projectedsa.models.GameCredentials;
import com.example.projectedsa.models.Inventory;
import com.example.projectedsa.models.Item;
import com.example.projectedsa.models.RegisterCredentials;
import com.example.projectedsa.models.StoreCredentials;
import com.example.projectedsa.models.User;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface API {

    public static final String BASE_URL = "http://147.83.7.206:8080/dsaApp/";

    //UserService

    @POST("user/register")
    Call<User> register(@Body RegisterCredentials user);

    @PUT("user/update/{name}")
    Call<User> updateUser(@Path("name") String oldName, @Body RegisterCredentials user);

    @PUT("user/update/{name}/coins")
    Call<User> updateUserCoins(@Path("name") String name, @Body CoinsCredentials cCr);

    @DELETE("user/delete/{name}")
    Call<Void> deleteUser(@Path("name") String name);

    @POST("user/logIn")
    Call<User> login(@Body RegisterCredentials user);

    //GameService

    @POST("game/add")
    Call<Game> addGame(@Body GameCredentials gCr);

    @GET ("game/{userName}")
    Call<Game> getGame(@Path("userName") String name);

    @PUT("game/update/{userName}")
    Call<Game> updateGame(@Path("userName") String name, @Body GameCredentials gCr);

    @GET ("game/byPoints")
    Call<List<Game>> getGamesByPoints();

    //StoreService

    @GET("store/itemList")
    Call<List<Item>> getAllItems();

    @PUT("store/buyItem/{itemName}")
    Call<Item> buyItem(@Path("itemName") String itemName, @Body StoreCredentials sCr);

    @PUT("store/collectItem/{itemName}")
    Call<Item> collectItem(@Path("itemName") String itemName, @Body StoreCredentials sCr);

    @GET("store/userInventoryList/{userName}")
    Call<List<Inventory>> getUserInventory(@Path("userName") String name);

    @PUT("store/useItem/{itemName}")
    Call<Item> useItem(@Path("itemName") String itemName, @Body StoreCredentials sCr);

}
