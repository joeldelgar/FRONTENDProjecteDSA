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
import com.example.projectedsa.api.CredentialsReq;
import com.example.projectedsa.api.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView user = (TextView) findViewById(R.id.user_text);
        TextView psw = (TextView) findViewById(R.id.psw_text);
        Button loginbtn = (Button) findViewById(R.id.button);
        Button registerbtn = (Button) findViewById(R.id.button2);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = user.getText().toString();
                String password = psw.getText().toString();

                Gson gson = new GsonBuilder().setLenient().create();
                Retrofit retrofit = new Retrofit.Builder().baseUrl(API.BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
                API gerritAPI = retrofit.create(API.class);
                Call<User> call = gerritAPI.login(new CredentialsReq(userName,password));
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        int variable = response.code();
                        if(response.isSuccessful()){
                            User user =  response.body();
                            Log.i("LOGIN", "OK"+user);
                            Intent intent = new Intent(getApplicationContext(), PrincipalActivity.class);
                            startActivity(intent);
                        }
                    }
                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Log.e("LOGIN", "ERROR",t);
                        Toast.makeText(MainActivity.this, "Usuari i/o Contrasenya Incorrectes", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = user.getText().toString();
                String password = psw.getText().toString();

                Gson gson = new GsonBuilder().setLenient().create();
                Retrofit retrofit = new Retrofit.Builder().baseUrl(API.BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
                API gerritAPI = retrofit.create(API.class);
                Call<User> call = gerritAPI.register(new CredentialsReq(userName,password));
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        int variable = response.code();
                        if(response.isSuccessful()){
                            User user = response.body();
                            Log.i("REGISTER", "OK"+user);
                            Intent intent = new Intent(getApplicationContext(), PrincipalActivity.class);
                            startActivity(intent);
                        }
                    }
                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Log.e("REGISTER", "ERROR",t);
                        Toast.makeText(MainActivity.this, "ERROR AL CREAR USUARI", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}