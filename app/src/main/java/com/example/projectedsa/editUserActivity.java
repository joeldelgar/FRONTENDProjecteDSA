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

public class editUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        Button edit = (Button) findViewById(R.id.Edit_btn);
        Button delete = (Button) findViewById(R.id.deleteUser_btn);
        TextView Name = (TextView) findViewById(R.id.userNametxt);
        TextView Psw = (TextView) findViewById(R.id.pswUsertxt);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = Name.getText().toString();
                String userPsw = Psw.getText().toString();

                Gson gson = new GsonBuilder().setLenient().create();
                Retrofit retrofit = new Retrofit.Builder().baseUrl(API.BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
                API gerritAPI = retrofit.create(API.class);
                Call<User> call = gerritAPI.updateUser(new CredentialsReq(userName, userPsw));
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        int variable = response.code();
                        if(response.isSuccessful()){
                            User user = response.body();
                            Log.i("UPDATE", "OK"+user);
                            Toast.makeText(editUserActivity.this, "USUARI EDITAT CORRECTAMENT", Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(editUserActivity.this, "EL NOM D'USUARI INTRODUIT JA EXISTEIX, PORVA AMB UN ALTRE DIFERENT", Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Log.e("REGISTER", "ERROR",t);
                        Toast.makeText(editUserActivity.this, "ERROR AL EDITAR USUARI", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = Name.getText().toString();

                Gson gson = new GsonBuilder().setLenient().create();
                Retrofit retrofit = new Retrofit.Builder().baseUrl(API.BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
                API gerritAPI = retrofit.create(API.class);
                Call<User> call = gerritAPI.deleteUser(name);
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        int variable = response.code();
                        if(response.isSuccessful()){
                            User user = response.body();
                            Log.i("DELETE", "OK"+user);
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                            Toast.makeText(editUserActivity.this, "USUARI ELIMINAT CORRECTAMENT", Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(editUserActivity.this, "L'USUARI NO S'HA POGUT ELIMINAR", Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Log.e("DELETE", "ERROR",t);
                        Toast.makeText(editUserActivity.this, "ERROR AL ELIMINAR USUARI", Toast.LENGTH_LONG).show();
                    }
                });

            }
        });
    }
}