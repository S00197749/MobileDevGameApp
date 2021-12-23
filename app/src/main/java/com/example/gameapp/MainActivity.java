package com.example.gameapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    int blue = 1;
    int red = 2;
    int yellow = 3;
    int green = 4;

    Button btnGreen, btnBlue, btnRed, btnYellow;
    int sequenceCount = 4, n = 0;
    Object mutex = new Object();
    String[] gameSequence = new String[120];
    int arrayIndex = 0;

    CountDownTimer ct = new CountDownTimer(6000, 1500) {
        @Override
        public void onTick(long l) {
            oneButton();
        }

        @Override
        public void onFinish() {
            for(int i = 0; i < arrayIndex; i++){
                Log.d("game sequence", gameSequence[i]);
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGreen = findViewById(R.id.btnGreen);
        btnBlue = findViewById(R.id.btnBlue);
        btnRed = findViewById(R.id.btnRed);
        btnYellow = findViewById(R.id.btnYellow);

        ct.start();
    }

    public void doPlay(View view) {
        Intent intent = new Intent(this, PlayScreen.class);
        intent.putExtra("gameSequence", gameSequence);
        startActivity(intent);
    }

    public void oneButton(){
        n = getRandom(sequenceCount);

        switch (n){
            case 1:
                flashButton(btnBlue);
                gameSequence[arrayIndex++] = "Blue";
                break;
            case 2:
                flashButton(btnRed);
                gameSequence[arrayIndex++] = "Red";
                break;
            case 3:
                flashButton(btnYellow);
                gameSequence[arrayIndex++] = "Yellow";
                break;
            case 4:
                flashButton(btnGreen);
                gameSequence[arrayIndex++] = "Green";
                break;
        }
    }

    private void flashButton(Button btn) {
        Handler handler = new Handler();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                btn.setPressed(true);
                btn.invalidate();
                btn.performClick();
                Handler handler1 = new Handler();
                Runnable r1 = new Runnable() {
                    @Override
                    public void run() {
                        btn.setPressed(false);
                        btn.invalidate();
                    }
                };
                handler1.postDelayed(r1, 600);
            }
        };
        handler.postDelayed(r, 600);
    }

    private int getRandom(int maxValue) {
        return ((int)((Math.random() * maxValue) + 1));
    }
}