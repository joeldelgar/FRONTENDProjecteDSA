package com.dsa.frontendprojecte.connections;

import com.dsa.frontendprojecte.models.CoinsCredentials;
import com.dsa.frontendprojecte.models.Game;
import com.dsa.frontendprojecte.models.GameCredentials;
import com.dsa.frontendprojecte.models.Inventory;
import com.dsa.frontendprojecte.models.Item;
import com.dsa.frontendprojecte.models.RegisterCredentials;
import com.dsa.frontendprojecte.models.StoreCredentials;
import com.dsa.frontendprojecte.models.User;

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

    @GET ("user/{name}")
    Call<User> getUser(@Path("name") String name);

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
    Call<Inventory> buyItem(@Path("itemName") String itemName, @Body StoreCredentials sCr);

    @PUT("store/collectItem/{itemName}")
    Call<Inventory> collectItem(@Path("itemName") String itemName, @Body StoreCredentials sCr);

    @GET("store/userInventoryList/{userName}")
    Call<List<Inventory>> getUserInventory(@Path("userName") String name);

    @PUT("store/useItem/{itemName}")
    Call<Inventory> useItem(@Path("itemName") String itemName, @Body StoreCredentials sCr);

}
