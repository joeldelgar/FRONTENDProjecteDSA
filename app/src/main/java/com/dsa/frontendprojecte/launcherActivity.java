package com.dsa.frontendprojecte;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.dsa.frontendprojecte.connections.API;
import com.dsa.frontendprojecte.connections.UnityConnect;
import com.dsa.frontendprojecte.models.CoinsCredentials;
import com.dsa.frontendprojecte.models.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.unity3d.player.UnityPlayerActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class launcherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        //getUser();
        String userName = UnityConnect.getUserName();
        Log.i("launcher","userName OK: " +userName);
        int coins = UnityConnect.getCoins();
        Log.i("launcher","coins OK: " +coins);
/*        int points = UnityConnect.getPoints();
        Log.i("launcher","points OK: " +points);
        int health = UnityConnect.getHealth();
        Log.i("launcher","health OK: " +health);
        String userInventory = UnityConnect.getUserInventory();
        Log.i("launcher","userInventory OK: " +userInventory);*/

/*        Intent intent = new Intent (getApplicationContext(), UnityPlayerActivity.class);
*//*        intent.putExtra("userName", userName);
        Log.i("intent","userName OK");
        intent.putExtra("coins", coins);*//*
*//*        intent.putExtra("points", points);
        intent.putExtra("health", health);
        intent.putExtra("userInventory", userInventory);*//*
        startActivity(intent);*/
    }
}