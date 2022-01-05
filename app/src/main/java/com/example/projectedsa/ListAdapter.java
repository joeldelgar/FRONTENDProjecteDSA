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
import com.example.projectedsa.api.Objecte;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder>{
    private List<Objecte> dades;
    private LayoutInflater mInflater;
    private Context context;

    //CONSTRUCTOR
    public ListAdapter (List<Objecte> itemList, Context context) {
        this.mInflater = LayoutInflater.from((Context) context);
        this.context = (Context) context;
        this.dades = itemList;
    }


    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = mInflater.inflate(R.layout.activity_object_list, null);
        return new ListAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ListAdapter.ViewHolder holder, final int position) {
        holder.bindData(dades.get(position));
    }


    @Override
    public int getItemCount() {
        return dades.size();
    }

    public void setItems(List<Objecte> items){
        dades=items;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView iconImage;
        TextView object_name, description;

        ViewHolder(View itemView){
            super(itemView);
            iconImage=itemView.findViewById(R.id.iconImagenView);
            object_name=itemView.findViewById(R.id.NameTextView);
            description=itemView.findViewById(R.id.DescriptionTextView);
        }

        void bindData(final Objecte item){
            object_name.setText(item.getName());
            description.setText(item.getDescription());
            Picasso.get().load("http://147.83.7.206:8080/"+item.getAvatar()).into(iconImage);
            object_name.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {

                    Log.d("Selected Object", item.getName());
                    Intent intent = new Intent(context, buy_objectActivity.class);
                    intent.putExtra("item", item);
                    context.startActivity(intent);
                }
            });

            iconImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("Selected Object", "Nom: "+item.getName());
                    Intent intent = new Intent(context, buy_objectActivity.class);
                    intent.putExtra("item", item);
                    context.startActivity(intent);
                }
            });

        }


    }
}
