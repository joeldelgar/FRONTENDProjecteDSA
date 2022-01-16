package com.example.projectedsa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.projectedsa.api.API;
import com.example.projectedsa.api.GameCredentials;
import com.example.projectedsa.api.Objecte;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class statsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        init();

        ImageButton back = (ImageButton) findViewById(R.id.b_btn);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (getApplicationContext(), PrincipalActivity.class);
                startActivity(intent);
            }
        });

    }

    public void init(){
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(API.BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
        API gerritAPI = retrofit.create(API.class);
        Call<List<GameCredentials>> call = gerritAPI.getGamesByPoints();
        call.enqueue(new Callback<List<GameCredentials>>() {
            @Override
            public void onResponse(Call<List<GameCredentials>> call, Response<List<GameCredentials>> response) {
                int variable = response.code();
                Log.i("GamesByPoints CODE",":"+variable);
                List<GameCredentials> gamesList =  response.body();
                GamesAdapter listAdapter =new GamesAdapter(gamesList, statsActivity.this);
                RecyclerView recyclerView3 = findViewById(R.id.GamesRecyclerView);
                recyclerView3.setHasFixedSize(true);
                recyclerView3.setLayoutManager(new LinearLayoutManager(statsActivity.this));
                recyclerView3.setAdapter(listAdapter);
                findViewById(R.id.progressBar3).setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<List<GameCredentials>> call, Throwable t) {
                Log.e("GameList", "ERROR",t);
                Toast.makeText(statsActivity.this, "Error al carregar la tenda", Toast.LENGTH_LONG).show();
            }
        });

    }
}