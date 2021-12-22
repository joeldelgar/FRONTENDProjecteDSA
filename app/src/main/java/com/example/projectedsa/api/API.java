package com.example.projectedsa.api;

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
    @POST("user/logIn")
    Call<User> login(@Body CredentialsReq user);

    @POST("user/register")
    Call<User> register(@Body CredentialsReq user);

    @GET("store/itemList")
    Call<List<Objecte>> getAllItems();

    @POST("store/buyItem")
    Call<StoreCredentials> buyItem(@Body StoreCredentials sCr);

    @PUT("user/update")
    Call<User> updateUser(@Body CredentialsReq user, String oldName);

    @DELETE("user/delete/{name}")
    Call<Void> deleteUser(@Path("name") String name);

}
