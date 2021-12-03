package com.example.projectedsa.api;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface API {

    public static final String BASE_URL = "http://10.0.2.2:8080";
    @POST("/dsaApp/user/login")
    Call<User> login(@Body CredentialsReq user);

    @POST("/dsaApp/user/add")
    Call<User> register(@Body CredentialsReq user);

    @GET("dsaApp/Game")
    Call<User> store();

}
