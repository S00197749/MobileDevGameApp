package com.example.gameapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;

public class PlayScreen extends AppCompatActivity implements SensorEventListener {
    int score;
    String selectedColour;
    Boolean colourSelected;
    String[] gameSequence = new String[120];
    String[] selection = new String[4];
    private SensorManager mSensorManager;
    private Sensor mSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_screen);

        gameSequence = getIntent().getStringArrayExtra("gameSequence");

        //Accelerometer
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    public void doGoToAddPlayer(View view) {
        Intent intent = new Intent(this, AddPlayer.class);
        //intent.putExtra("score", score);
        startActivity(intent);
    }

    public void doGoToHiScores(View view) {
        Intent intent = new Intent(this, HiScores.class);
        startActivity(intent);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // called by the system every x ms
        float x, y, z;

        x = event.values[0]; // get x value
        y = Math.abs(event.values[1]);
        z = Math.abs(event.values[2]);

        if(x >= 7){
            selectedColour = "Blue";
            colourSelected = true;
        }
        else if(x <= -7) {
            selectedColour = "Yellow";
            colourSelected = true;
        }

        if(colourSelected){

        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}