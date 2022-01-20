package com.example.projectedsa.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.projectedsa.R;
import com.example.projectedsa.models.Inventory;
import com.squareup.picasso.Picasso;

import java.util.List;

public class InventoryListAdapter extends RecyclerView.Adapter<InventoryListAdapter.ViewHolder>{
    private List<Inventory> dades2;
    private LayoutInflater mInflater;
    private Context context;

    //CONSTRUCTOR
    public InventoryListAdapter(List<Inventory> inventoryList, Context context) {
        this.mInflater = LayoutInflater.from((Context) context);
        this.context = (Context) context;
        this.dades2 = inventoryList;
    }


    @Override
    public InventoryListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = mInflater.inflate(R.layout.activity_inventory_list, null);
        return new InventoryListAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final InventoryListAdapter.ViewHolder holder, final int position2) {
        holder.bindData(dades2.get(position2));
    }


    @Override
    public int getItemCount() {
        return dades2.size();
    }

    public void setItems(List<Inventory> items){
        dades2=items;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView iconImage;
        TextView object_name, quantity, description;

        ViewHolder(View itemView){
            super(itemView);
            iconImage=itemView.findViewById(R.id.imageAvatar);
            object_name=itemView.findViewById(R.id.UserNameGame);
            description = itemView.findViewById(R.id.PointsGame);
            quantity=itemView.findViewById(R.id.quantity);
        }

        void bindData(final Inventory inventory){
            object_name.setText(inventory.getItemName());
            description.setText(inventory.getItemDescription());
            quantity.setText("x"+inventory.getItemQuantity());
            Picasso.get().load("http://147.83.7.206:8080/"+inventory.getItemAvatar()).into(iconImage);
        }
    }
}
