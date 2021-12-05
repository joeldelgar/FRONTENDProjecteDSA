package com.example.projectedsa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class buy_objectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_object);

        Button exit = (Button) findViewById(R.id.exit_btn);
        Button buy = (Button) findViewById(R.id.buy_btn);

        TextView objectName = (TextView) findViewById(R.id.TextObjectName);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        objectName.setText(name);

        TextView description = (TextView) findViewById(R.id.TextObjectDes);
        Intent intent2 = getIntent();
        String des = intent2.getStringExtra("description");
        description.setText(des);

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