package com.dsa.frontendprojecte;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.dsa.frontendprojecte.connections.API;
import com.dsa.frontendprojecte.models.Inventory;
import com.dsa.frontendprojecte.models.StoreCredentials;
import com.dsa.frontendprojecte.models.Item;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class buyItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_item);

        Button exit = (Button) findViewById(R.id.exit_btn);
        Button buy = (Button) findViewById(R.id.buy_btn);

        TextView objectName = (TextView) findViewById(R.id.TextObjectName);
        Intent intent = getIntent();
        Item item = (Item)intent.getSerializableExtra("item");
        if (item!=null) {
            Log.d("CONTROL", "BUY"+item.getName());
        }else{
            Log.d("CONTROL", "Objecte NULL");
        }
        objectName.setText(item.getName());
        TextView description = (TextView) findViewById(R.id.TextObjectDes);
        description.setText(item.getDescription());

        TextView preu = (TextView) findViewById(R.id.TexObjectPrice);
        preu.setText(item.getCost());

        SharedPreferences sharedPref = getSharedPreferences("myPref", Context.MODE_PRIVATE);
        String userName = sharedPref.getString("User",null);

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), storeActivity.class);
                startActivity(intent);
            }
        });

        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.activity_buy_item);
                Intent intent1 = getIntent();
                SharedPreferences sharedPref = getSharedPreferences("myPref", Context.MODE_PRIVATE);
                String userName = sharedPref.getString("User",null);
                Log.i("Name: ", userName);
                String itemName = objectName.getText().toString();
                Log.i("Item: ", itemName);

                Gson gson = new GsonBuilder().setLenient().create();
                Retrofit retrofit = new Retrofit.Builder().baseUrl(API.BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
                API gerritAPI = retrofit.create(API.class);
                Call<Inventory> call = gerritAPI.buyItem(itemName, new StoreCredentials(itemName, userName));
                call.enqueue(new Callback<Inventory>() {
                    @Override
                    public void onResponse(Call<Inventory> call, Response<Inventory> response) {
                        int variable = response.code();
                        Inventory inventory = response.body();
                        String itemNom = inventory.getItemName();
                        Log.i("Buy CODE",":"+variable);
                        if (variable == 200) {
                            Intent intent = new Intent(getApplicationContext(), storeActivity.class);
                            startActivity(intent);
                            Toast.makeText(buyItemActivity.this, "Item: " + itemNom + "Adquirit", Toast.LENGTH_LONG).show();
                        }else{
                            Intent intent = new Intent(getApplicationContext(), storeActivity.class);
                            startActivity(intent);
                            Toast.makeText(buyItemActivity.this, "No tens suficients punts", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Inventory> call, Throwable t) {
                        Log.e("onFailure", "BUY ERROR",t);
                        Toast.makeText(buyItemActivity.this, "Error al comprar l'Item", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}