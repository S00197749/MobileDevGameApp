package com.example.gameapp;

import java.util.Date;

public class PlayerModel {
    private int ID;
    private String Name;
    private int Score;

    //constructors

    public PlayerModel() {
    }

    public PlayerModel(int ID, String name, int score) {
        this.ID = ID;
        Name = name;
        Score = score;
    }

    //toString

    @Override
    public String toString() {
        return "PlayerModel{" +
                "ID=" + ID +
                ", Name='" + Name + '\'' +
                ", Score=" + Score +
                '}';
    }

    //getters and setters

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getScore() {
        return Score;
    }

    public void setScore(int score) {
        Score = score;
    }
}
