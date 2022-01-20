package com.example.projectedsa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectedsa.connections.API;
import com.example.projectedsa.models.RegisterCredentials;
import com.example.projectedsa.models.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class registerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ImageButton b = (ImageButton) findViewById(R.id.imageButton);
        Button register = (Button) findViewById(R.id.button3);
        TextView name = (TextView) findViewById(R.id.nametxt);
        TextView psw = (TextView) findViewById(R.id.passwordtxt);
        TextView m = (TextView) findViewById(R.id.mailtxt);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = name.getText().toString();
                String password = psw.getText().toString();
                String mail = m.getText().toString();

                Gson gson = new GsonBuilder().setLenient().create();
                Retrofit retrofit = new Retrofit.Builder().baseUrl(API.BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
                API gerritAPI = retrofit.create(API.class);
                Call<User> call = gerritAPI.register(new RegisterCredentials(userName,password, mail));
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        int variable = response.code();
                        Log.i("REGISTER CODE", ":"+variable);
                        if(response.isSuccessful()){
                            User user = response.body();
                            Log.i("REGISTER", "OK"+user);
                            Intent intent = new Intent(getApplicationContext(), SplashActivity.class);
                            startActivity(intent);
                            SharedPreferences sharedPref = getSharedPreferences("myPref", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPref.edit();
                            editor.putString("User", userName);
                            editor.putString("psw", password);
                            editor.commit();
                        }
                        else{
                            Toast.makeText(registerActivity.this, "EL NOM D'USUARI INTRODUIT JA EXISTEIX, PORVA AMB UN ALTRE DIFERENT", Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Log.e("REGISTER", "ERROR",t);
                        Toast.makeText(registerActivity.this, "ERROR AL CREAR USUARI", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}