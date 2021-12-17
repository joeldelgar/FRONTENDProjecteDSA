package com.example.projectedsa;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
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

import java.util.List;

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
            Log.d("JOEL", "a BUY"+objecte.getNom());
        }else{
            Log.d("JOEL", "Objecte NULL");
        }
        objectName.setText(objecte.getNom());
        TextView description = (TextView) findViewById(R.id.TextObjectDes);
        description.setText(objecte.getDescription());

        TextView preu = (TextView) findViewById(R.id.TexObjectPrice);
        preu.setText(objecte.getPoints());

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
                User user = (User)intent1.getSerializableExtra("name");
                String userName = user.getName();
                String itemName = objectName.getText().toString();

                Gson gson = new GsonBuilder().setLenient().create();
                Retrofit retrofit = new Retrofit.Builder().baseUrl(API.BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
                API gerritAPI = retrofit.create(API.class);
                Call<Objecte> call = gerritAPI.buyItem(new StoreCredentials(itemName, userName));
                call.enqueue(new Callback<Objecte>() {
                    @Override
                    public void onResponse(Call<Objecte> call, Response<Objecte> response) {
                        Toast.makeText(buy_objectActivity.this, "Item: "+itemName+"Adquirit", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<Objecte> call, Throwable t) {
                        Log.e("STORE", "ERROR",t);
                        Toast.makeText(buy_objectActivity.this, "Error al comprar l'Item", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}