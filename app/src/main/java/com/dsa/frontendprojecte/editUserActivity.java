package com.dsa.frontendprojecte;

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

import com.dsa.frontendprojecte.MainActivity;
import com.dsa.frontendprojecte.PrincipalActivity;
import com.dsa.frontendprojecte.R;
import com.dsa.frontendprojecte.connections.API;
import com.dsa.frontendprojecte.models.RegisterCredentials;
import com.dsa.frontendprojecte.models.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class editUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        Button edit = (Button) findViewById(R.id.Edit_btn);
        Button delete = (Button) findViewById(R.id.deleteUser_btn);
        ImageButton back = (ImageButton) findViewById(R.id.bck);
        TextView name = (TextView) findViewById(R.id.userNametxt);
        TextView psw = (TextView) findViewById(R.id.pswUsertxt);

        SharedPreferences sharedPref = getSharedPreferences("myPref", Context.MODE_PRIVATE);
        String userName = sharedPref.getString("User",null);
        String userPsw = sharedPref.getString("psw",null);
        String userMail = sharedPref.getString("mail", null);

        name.setText(userName);
        psw.setText(userPsw);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PrincipalActivity.class);
                startActivity(intent);
            }
        });


        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String n = name.getText().toString();
                String p = psw.getText().toString();

                Log.i("oldName", "/"+userName);
                Log.i("newName", "/"+n);
                Log.i("Psw", "/"+p);
                Log.i("Mail", "/"+userMail);


                Gson gson = new GsonBuilder().setLenient().create();
                Retrofit retrofit = new Retrofit.Builder().baseUrl(API.BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
                API gerritAPI = retrofit.create(API.class);
                Call<User> call = gerritAPI.updateUser(userName, new RegisterCredentials(n, p, userMail));
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        int variable = response.code();
                        Log.i("UPDATE CODE", "/"+variable);
                        if(response.isSuccessful()){
                            User user = response.body();
                            Log.i("UPDATE", "OK"+user);
                            Toast.makeText(editUserActivity.this, "USUARI EDITAT CORRECTAMENT", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(editUserActivity.this, "EL NOM D'USUARI INTRODUIT JA EXISTEIX, PORVA AMB UN ALTRE DIFERENT", Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Log.e("UPDATE", "ERROR",t);
                        Toast.makeText(editUserActivity.this, "ERROR AL EDITAR USUARI", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usrName = name.getText().toString();

                Gson gson = new GsonBuilder().setLenient().create();
                Retrofit retrofit = new Retrofit.Builder().baseUrl(API.BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
                API gerritAPI = retrofit.create(API.class);
                Call<Void> call = gerritAPI.deleteUser(usrName);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        int variable = response.code();
                        Log.i("DELETE CODE",":"+variable);
                        if(response.isSuccessful()){
                            Log.i("DELETE", "OK");
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                            Toast.makeText(editUserActivity.this, "USUARI ELIMINAT CORRECTAMENT", Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Log.e("DELETE", "ERROR",t);
                        Toast.makeText(editUserActivity.this, "ERROR AL ELIMINAR USUARI", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}