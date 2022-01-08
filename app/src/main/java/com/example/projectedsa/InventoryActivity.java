package com.example.projectedsa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.projectedsa.api.API;
import com.example.projectedsa.api.Inventory;
import com.example.projectedsa.api.Objecte;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InventoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);
        ImageButton exit = (ImageButton) findViewById(R.id.exit);

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PrincipalActivity.class);
                startActivity(intent);
            }
        });
    }

    public void init(){
        SharedPreferences sharedPref = getSharedPreferences("myPref", Context.MODE_PRIVATE);
        String userName = sharedPref.getString("User",null);
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(API.BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
        API gerritAPI = retrofit.create(API.class);
        Call<List<Inventory>> call = gerritAPI.getUserInventory(userName);
        call.enqueue(new Callback<List<Inventory>>() {
            @Override
            public void onResponse(Call<List<Inventory>> call, Response<List<Inventory>> response) {
                int variable = response.code();
                Log.i("InventaryList CODE",":"+variable);
                List<Inventory> inventoryList =  response.body();
                InventoryListAdapter listAdapter =new InventoryListAdapter(inventoryList, InventoryActivity.this);
                RecyclerView recyclerView = findViewById(R.id.ListRecicleView);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(InventoryActivity.this));
                recyclerView.setAdapter(listAdapter);
            }

            @Override
            public void onFailure(Call<List<Inventory>> call, Throwable t) {
                Log.e("STORE", "ERROR",t);
                Toast.makeText(InventoryActivity.this, "Error al carregar la tenda", Toast.LENGTH_LONG).show();
            }
        });
    }
}