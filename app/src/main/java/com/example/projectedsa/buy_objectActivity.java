package com.example.projectedsa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class buy_objectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_object);
    }

    public void onClick (View v){
        TextView nameobject = (TextView) findViewById(R.id.TextObjectName);
        TextView description = (TextView) findViewById(R.id.TextObjectDes);
        TextView price = (TextView) findViewById(R.id.TexObjectPrice);
        int b =v.getId();
        try{
            switch (b){
                case R.id.exit_btn:
                    Intent intent = new Intent(this, storeActivity.class);
                    break;
                case R.id.buy_btn:
                    //API REST PER AFEGIR OBJECTE A LA LLISTA D'OBJECTES DEL USUARI
                    break;
            }

        }catch (Exception e){
            nameobject.setText("ERROR!");
            description.setText("ERROR!");
            price.setText("ERROR!");
        }
    }
}