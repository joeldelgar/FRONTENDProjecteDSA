package com.dsa.frontendprojecte;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.dsa.frontendprojecte.connections.API;
import com.dsa.frontendprojecte.connections.UnityConnect;
import com.dsa.frontendprojecte.models.Inventory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.unity3d.player.UnityPlayerActivity;

import java.io.Serializable;
import java.util.List;

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

        String userName = UnityConnect.getUserName();
        int coins = UnityConnect.getCoins();
        int points = UnityConnect.getPoints();
        int health = UnityConnect. getHealth();
        String userInventory = UnityConnect.getUserInventory();

        Intent intent = new Intent (getApplicationContext(), UnityPlayerActivity.class);
        intent.putExtra("userName", userName);
        intent.putExtra("coins", coins);
        intent.putExtra("points", points);
        intent.putExtra("health", health);
        intent.putExtra("userInventory", userInventory);
        startActivity(intent);
    }
}