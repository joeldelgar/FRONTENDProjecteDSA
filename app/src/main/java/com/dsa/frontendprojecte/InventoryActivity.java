package com.dsa.frontendprojecte;

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

import com.dsa.frontendprojecte.adapters.InventoryListAdapter;
import com.dsa.frontendprojecte.connections.API;
import com.dsa.frontendprojecte.models.Inventory;
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
        init();

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
                if(inventoryList == null){
                    Toast.makeText(InventoryActivity.this, "No tens res al inventari", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), PrincipalActivity.class);
                    startActivity(intent);
                }
                InventoryListAdapter listAdapter =new InventoryListAdapter(inventoryList, InventoryActivity.this);
                RecyclerView recyclerView2 = findViewById(R.id.recyclerView2);
                recyclerView2.setHasFixedSize(true);
                recyclerView2.setLayoutManager(new LinearLayoutManager(InventoryActivity.this));
                recyclerView2.setAdapter(listAdapter);
                findViewById(R.id.progressBar).setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<List<Inventory>> call, Throwable t) {
                Log.e("STORE", "ERROR",t);
                Toast.makeText(InventoryActivity.this, "Error al carregar la tenda", Toast.LENGTH_LONG).show();
            }
        });
    }
}