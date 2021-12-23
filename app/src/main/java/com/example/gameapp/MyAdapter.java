package com.example.gameapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<PlayerModel> allPlayers;

    MyAdapter(Context context, ArrayList<PlayerModel> allPlayers){
        this.context = context;
        this.allPlayers = allPlayers;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.tvPlayerName.setText(String.valueOf(allPlayers.get(position).getName()));
        holder.tvPlayerScore.setText(String.valueOf(allPlayers.get(position).getScore()));
        holder.tvPosition.setText(String.valueOf(position + 1));
    }

    @Override
    public int getItemCount() {

        return allPlayers.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvPlayerName, tvPlayerScore, tvPosition;
        LinearLayout mainLayout;
        CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvPlayerName = itemView.findViewById(R.id.tvPlayerName);
            tvPlayerScore = itemView.findViewById(R.id.tvPlayerScore);
            tvPosition = itemView.findViewById(R.id.tvPosition);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            cardView = itemView.findViewById(R.id.card);
        }
    }
}
