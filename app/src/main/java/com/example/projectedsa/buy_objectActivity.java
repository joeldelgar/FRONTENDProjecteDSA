package com.example.projectedsa;

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

import com.example.projectedsa.api.API;
import com.example.projectedsa.api.Objecte;
import com.example.projectedsa.api.StoreCredentials;
import com.example.projectedsa.api.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class buy_objectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_object);

        Button exit = (Button) findViewById(R.id.exit_btn);
        Button buy = (Button) findViewById(R.id.buy_btn);

        TextView objectName = (TextView) findViewById(R.id.TextObjectName);
        Intent intent = getIntent();
        Objecte objecte = (Objecte)intent.getSerializableExtra("item");
        if (objecte!=null) {
            Log.d("JOEL", "BUY"+objecte.getName());
        }else{
            Log.d("JOEL", "Objecte NULL");
        }
        objectName.setText(objecte.getName());
        TextView description = (TextView) findViewById(R.id.TextObjectDes);
        description.setText(objecte.getDescription());

        TextView preu = (TextView) findViewById(R.id.TexObjectPrice);
        preu.setText(objecte.getCost());

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
                setContentView(R.layout.activity_buy_object);
                Intent intent1 = getIntent();
                SharedPreferences sharedPref = getSharedPreferences("myPref", Context.MODE_PRIVATE);
                String userName = sharedPref.getString("User",null);
                Log.i("Name: ", userName);
                String itemName = objectName.getText().toString();
                Log.i("Item: ", itemName);

                Gson gson = new GsonBuilder().setLenient().create();
                Retrofit retrofit = new Retrofit.Builder().baseUrl(API.BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
                API gerritAPI = retrofit.create(API.class);
                Call<StoreCredentials> call = gerritAPI.buyItem(new StoreCredentials(itemName, userName));
                call.enqueue(new Callback<StoreCredentials>() {
                    @Override
                    public void onResponse(Call<StoreCredentials> call, Response<StoreCredentials> response) {
                        int variable = response.code();
                        Log.i("Buy CODE",":"+variable);
                        if (variable == 200) {
                            Intent intent = new Intent(getApplicationContext(), storeActivity.class);
                            startActivity(intent);
                            Toast.makeText(buy_objectActivity.this, "Item: " + itemName + "Adquirit", Toast.LENGTH_LONG).show();
                        }else{
                            Intent intent = new Intent(getApplicationContext(), storeActivity.class);
                            startActivity(intent);
                            Toast.makeText(buy_objectActivity.this, "No tens suficients punts", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<StoreCredentials> call, Throwable t) {
                        Log.e("onFailure", "BUY ERROR",t);
                        Toast.makeText(buy_objectActivity.this, "Error al comprar l'Item", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}