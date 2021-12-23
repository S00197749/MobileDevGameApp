package com.example.gameapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddPlayer extends AppCompatActivity {

    int score;
    EditText etPlayerName, etScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_player);

        etPlayerName = findViewById(R.id.etPlayerName);
        etScore = findViewById(R.id.etScore);
        Toast.makeText(AddPlayer.this, etScore.getText().toString(),
                Toast.LENGTH_SHORT).show();
        //score = getIntent().getExtras().getInt("score");

    }

    public void doAdd(View view) {

        score = Integer.parseInt(etScore.getText().toString());

        PlayerModel playerModel;
        DataBaseHelper dataBaseHelper = new DataBaseHelper(AddPlayer.this);

        try{
            playerModel = new PlayerModel(-1, etPlayerName.getText().toString(),
                    score);

            boolean success = dataBaseHelper.addPlayer(playerModel);

            Toast.makeText(AddPlayer.this, "Added Player",
                    Toast.LENGTH_SHORT).show();

        }
        catch (Exception e){
            Toast.makeText(AddPlayer.this, "Error adding player",
                    Toast.LENGTH_SHORT).show();

        }
        Intent intent = new Intent(this, HiScores.class);
        startActivity(intent);
    }
}