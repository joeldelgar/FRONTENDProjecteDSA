package com.example.projectedsa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.projectedsa.api.API;
import com.example.projectedsa.api.CredentialsReq;
import com.example.projectedsa.api.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PrincipalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        ImageButton store = (ImageButton) findViewById(R.id.store_btn);
        ImageButton edit = (ImageButton) findViewById(R.id.editUser_btn);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (getApplicationContext(), editUserActivity.class);
                startActivity(intent);
            }
        });

        store.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), storeActivity.class);
                startActivity(intent);
                Gson gson = new GsonBuilder().setLenient().create();
                Retrofit retrofit = new Retrofit.Builder().baseUrl(API.BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
                API gerritAPI = retrofit.create(API.class);
                Call<User> call = gerritAPI.store();
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        User user =  response.body();
                        Log.i("STORE", "OK"+user);
                        Intent intent = new Intent(getApplicationContext(), PrincipalActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Log.e("STORE", "ERROR",t);
                        Toast.makeText(PrincipalActivity.this, "Error al carregar la tenda", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}