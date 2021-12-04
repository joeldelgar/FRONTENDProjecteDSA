package com.example.projectedsa;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.projectedsa.api.Objecte;

import java.util.ArrayList;
import java.util.List;

public class storeActivity extends AppCompatActivity {

    List<Objecte> objectList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        init();
        ImageButton exit = (ImageButton) findViewById(R.id.return_btn);

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PrincipalActivity.class);
                startActivity(intent);
            }
        });
    }

    public void init(){
        objectList = new ArrayList<>();
        objectList.add(new Objecte("Ganzua", "Et permet obrir qualsevol porta", "2000"));
        objectList.add(new Objecte("Botes silencioses", "Els enemics no et detectaràn tan facilment", "1000"));
        objectList.add(new Objecte("Ulleres de Visió Nocturna", "Et permeten veure en la foscor", "3000"));
        objectList.add(new Objecte("Llanterna", "Et permet iluminar", "4000"));
        objectList.add(new Objecte("Gorro Negre", "Et manté calent en dies de fred", "500"));

        ListAdapter listAdapter =new ListAdapter(objectList, this);
        RecyclerView recyclerView = findViewById(R.id.ListRecicleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listAdapter);
    }
    public void onClick (View v){
        Intent intent = new Intent(this, buy_objectActivity.class);
        startActivity(intent);
    }
}