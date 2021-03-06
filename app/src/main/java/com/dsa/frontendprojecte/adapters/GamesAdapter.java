package com.dsa.frontendprojecte.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.dsa.frontendprojecte.R;
import com.dsa.frontendprojecte.models.Game;


import java.util.List;

public class GamesAdapter extends RecyclerView.Adapter<GamesAdapter.ViewHolder>{
    private List<Game> dades3;
    private LayoutInflater mInflater;
    private Context context;

    //CONSTRUCTOR
    public GamesAdapter(List<Game> gamesList, Context context) {
        this.mInflater = LayoutInflater.from((Context) context);
        this.context = (Context) context;
        this.dades3 = gamesList;
    }

    @Override
    public GamesAdapter.ViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        View v = mInflater.inflate(R.layout.activity_games_list, null);
        return new GamesAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(GamesAdapter.ViewHolder holder, int position3) {
        holder.bindData(dades3.get(position3));
    }

    @Override
    public int getItemCount() {
        return dades3.size();
    }

    public void setItems(List<Game> items){
        dades3=items;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView userNameGame, pointsGame;

        ViewHolder(View itemView){
            super(itemView);
            userNameGame = itemView.findViewById(R.id.UserNameGame);
            pointsGame = itemView.findViewById(R.id.PointsGame);
        }

        void bindData(final Game games){
            userNameGame.setText(games.getUserName());
            pointsGame.setText("x"+games.getPoints());
        }
    }
}
