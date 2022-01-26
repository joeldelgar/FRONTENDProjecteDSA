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
import com.dsa.frontendprojecte.connections.UnityConnect;
import com.dsa.frontendprojecte.models.RegisterCredentials;
import com.dsa.frontendprojecte.models.User;
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
                Call<User> call = gerritAPI.login(new RegisterCredentials(userName,password, null));
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        int variable = response.code();
                        Log.i("LOGIN CODE", ":"+variable);
                        if(response.isSuccessful()){
                            User user =  response.body();
                            String userNom = user.getName();
                            Log.i("Name", ":"+userNom);
                            String userPsw = user.getPsw();
                            Log.i("Psw", ":"+userPsw);
                            String userMail = user.getMail();
                            Log.i("Mail", ":"+userMail);

                            Log.i("LOGIN", "OK"+user);
                            Intent intent = new Intent(getApplicationContext(), SplashActivity.class);
                            startActivity(intent);
                            SharedPreferences sharedPref = getSharedPreferences("myPref", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPref.edit();
                            editor.putString("User", userNom);
                            editor.putString("psw", userPsw);
                            editor.putString("mail", userMail);
                            editor.commit();

                            Toast.makeText(MainActivity.this, "Usuari i Contrasenya Correctes", Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(MainActivity.this, "Usuari i/o Contrasenya Incorrectes", Toast.LENGTH_LONG).show();
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
                Intent intent = new Intent(getApplicationContext(), registerActivity.class);
                startActivity(intent);
            }
        });
    }
}