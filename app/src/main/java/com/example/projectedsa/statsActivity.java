package com.example.projectedsa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class statsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        ImageButton back = (ImageButton) findViewById(R.id.b_btn);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (getApplicationContext(), PrincipalActivity.class);
                startActivity(intent);
            }
        });

    }

    //Funció per a poder obtenir el ranking de punts.
    public void init(){

    }
}