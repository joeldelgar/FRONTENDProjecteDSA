package com.example.projectedsa;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.projectedsa.api.Inventory;
import com.example.projectedsa.api.Objecte;

import java.util.List;

public class InventoryListAdapter extends RecyclerView.Adapter<InventoryListAdapter.ViewHolder>{
    private List<Inventory> dades;
    private LayoutInflater mInflater;
    private Context context;

    //CONSTRUCTOR
    public InventoryListAdapter(List<Inventory> inventoryList, Context context) {
        this.mInflater = LayoutInflater.from((Context) context);
        this.context = (Context) context;
        this.dades = inventoryList;
    }


    @Override
    public InventoryListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = mInflater.inflate(R.layout.activity_object_list, null);
        return new InventoryListAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final InventoryListAdapter.ViewHolder holder, final int position) {
        holder.bindData(dades.get(position));
    }


    @Override
    public int getItemCount() {
        return dades.size();
    }

    public void setItems(List<Inventory> items){
        dades=items;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView iconImage;
        TextView object_name, quantity;

        ViewHolder(View itemView){
            super(itemView);
            iconImage=itemView.findViewById(R.id.iconImagenView);
            object_name=itemView.findViewById(R.id.NameTextView);
            quantity=itemView.findViewById(R.id.DescriptionTextView);
        }

        void bindData(final Inventory inventory){
            //object_name.setText(inventory.);
            //quantity.setText(item.getDescription());
            object_name.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {

                    //Log.d("Selected Object", item.getName());
                    Intent intent = new Intent(context, buy_objectActivity.class);
                    //intent.putExtra("item", item);
                    context.startActivity(intent);


                }
            });

            iconImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Log.d("Selected Object", "Nom: "+item.getName());
                    Intent intent = new Intent(context, buy_objectActivity.class);
                    //intent.putExtra("item", item);
                    context.startActivity(intent);
                }
            });

        }


    }
}
