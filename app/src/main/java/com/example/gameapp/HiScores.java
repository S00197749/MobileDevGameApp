package com.example.gameapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class HiScores extends AppCompatActivity {
    RecyclerView recyclerView;
    DataBaseHelper dataBaseHelper;
    ArrayList<PlayerModel> allPlayers = new ArrayList<>();
    MyAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hi_scores);

        recyclerView = findViewById(R.id.recyclerView);

        dataBaseHelper = new DataBaseHelper(HiScores.this);

        listPlayers();
    }

    public void listPlayers(){
        allPlayers = dataBaseHelper.readPlayers();

        customAdapter = new MyAdapter(HiScores.this, allPlayers);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(HiScores.this));
    }

    public void doPlayAgain(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}