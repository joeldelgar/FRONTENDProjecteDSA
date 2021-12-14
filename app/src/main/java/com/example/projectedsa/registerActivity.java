package com.example.projectedsa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class registerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ImageButton b = (ImageButton) findViewById(R.id.imageButton);
        Button register = (Button) findViewById(R.id.button3);
        TextView name = (TextView) findViewById(R.id.nametxt);
        TextView psw = (TextView) findViewById(R.id.passwordtxt);
        TextView mail = (TextView) findViewById(R.id.mailtxt);

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
                /*String userName = user.getText().toString();
                String password = psw.getText().toString();
                String mail = mail.getText().toString();

                Gson gson = new GsonBuilder().setLenient().create();
                Retrofit retrofit = new Retrofit.Builder().baseUrl(API.BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
                API gerritAPI = retrofit.create(API.class);
                Call<User> call = gerritAPI.register(new CredentialsReq(userName,password, mail));
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
                        else{
                            Toast.makeText(MainActivity.this, "EL NOM D'USUARI INTRODUIT JA EXISTEIX, PORVA AMB UN ALTRE DIFERENT", Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Log.e("REGISTER", "ERROR",t);
                        Toast.makeText(MainActivity.this, "ERROR AL CREAR USUARI", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });*/
            }
        });
    }
}