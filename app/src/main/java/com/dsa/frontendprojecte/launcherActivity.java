package com.dsa.frontendprojecte;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.unity3d.player.UnityPlayerActivity;

public class launcherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        //getUserInventoryList();

        Intent intent = new Intent (getApplicationContext(), UnityPlayerActivity.class);
        startActivity(intent);
    }

    /*public void getUserInventoryList(){
        SharedPreferences sharedPref = getSharedPreferences("myPref", Context.MODE_PRIVATE);
        String userName = sharedPref.getString("User",null);

        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(API.BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
        API gerritAPI = retrofit.create(API.class);
        Call<List<Inventory>> call = gerritAPI.getUserInventory(userName);
        call.enqueue(new Callback<List<Inventory>>() {
            @Override
            public void onResponse(Call<List<Inventory>> call, Response<List<Inventory>> response) {
                int variable = response.code();
                Log.i("InventoryList CODE",":"+variable);
                List<Inventory> inventoryList =  response.body();
                if(inventoryList == null){
                    Toast.makeText(launcherActivity.this, "No tens res al inventari", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), PrincipalActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent (getApplicationContext(), UnityPlayerActivity.class);
                    intent.putExtra("UserInventoryList", (Serializable) inventoryList);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<List<Inventory>> call, Throwable t) {
                Log.e("UserInventoryList", "ERROR",t);
                Toast.makeText(launcherActivity.this, "Error getUserInventoryList", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), PrincipalActivity.class);
                startActivity(intent);
            }
        });
    }*/
}