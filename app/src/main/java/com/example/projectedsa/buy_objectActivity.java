package com.example.projectedsa;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.projectedsa.api.Objecte;

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
                //
            }
        });
    }
}