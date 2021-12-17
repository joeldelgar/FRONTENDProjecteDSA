package com.example.projectedsa;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectedsa.api.API;
import com.example.projectedsa.api.Objecte;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class storeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        init();
        ImageButton exit = (ImageButton) findViewById(R.id.return_btn);

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PrincipalActivity.class);
                startActivity(intent);
            }
        });
    }

    public void init(){
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(API.BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
        API gerritAPI = retrofit.create(API.class);
        Call<List<Objecte>> call = gerritAPI.getAllItems();
        call.enqueue(new Callback<List<Objecte>>() {
            @Override
            public void onResponse(Call<List<Objecte>> call, Response<List<Objecte>> response) {
                int variable = response.code();
                Log.i("ItemList CODE",":"+variable);
                List<Objecte> objecteList =  response.body();
                Intent intent = new Intent(getApplicationContext(), storeActivity.class);
                startActivity(intent);
                ListAdapter listAdapter =new ListAdapter(objecteList, this);
                RecyclerView recyclerView = findViewById(R.id.ListRecicleView);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(storeActivity.this));
                recyclerView.setAdapter(listAdapter);
            }

            @Override
            public void onFailure(Call<List<Objecte>> call, Throwable t) {
                Log.e("STORE", "ERROR",t);
                Toast.makeText(storeActivity.this, "Error al carregar la tenda", Toast.LENGTH_LONG).show();
            }
        });
    }
}