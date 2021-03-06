package com.dsa.frontendprojecte;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.dsa.frontendprojecte.adapters.ListAdapter;
import com.dsa.frontendprojecte.connections.API;
import com.dsa.frontendprojecte.models.Item;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
        Call<List<Item>> call = gerritAPI.getAllItems();
        call.enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                int variable = response.code();
                Log.i("ItemList CODE",":"+variable);
                List<Item> itemList =  response.body();
                ListAdapter listAdapter =new ListAdapter(itemList, storeActivity.this);
                RecyclerView recyclerView = findViewById(R.id.ListRecicleView);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(storeActivity.this));
                recyclerView.setAdapter(listAdapter);
                findViewById(R.id.progressBar2).setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {
                Log.e("STORE", "ERROR",t);
                Toast.makeText(storeActivity.this, "Error al carregar la tenda", Toast.LENGTH_LONG).show();
            }
        });
    }
}