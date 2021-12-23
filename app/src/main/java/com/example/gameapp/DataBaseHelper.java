package com.example.gameapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DataBaseHelper extends SQLiteOpenHelper {
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

    private static final String PLAYER_TABLE = "player_table";
    private static final String COLUMN_PLAYER_NAME = "player_name";
    private static final String COLUMN_PLAYER_SCORE = "player_score";
    private static final String COLUMN_ID = "_id";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "highscore.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createPlayerTableStatement =
                "CREATE TABLE " + PLAYER_TABLE +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_PLAYER_NAME + " TEXT, " +
                        COLUMN_PLAYER_SCORE + " INTEGER)";

        db.execSQL(createPlayerTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + PLAYER_TABLE);
        onCreate(db);
    }

    public boolean addPlayer(PlayerModel player){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_PLAYER_NAME, player.getName());
        cv.put(COLUMN_PLAYER_SCORE, player.getScore());

        long insert = db.insertWithOnConflict(PLAYER_TABLE, null, cv, SQLiteDatabase.CONFLICT_REPLACE);
        if (insert == -1){
            db.close();
            return false;
        }
        else{
            db.close();
            return true;
        }

    }

    public boolean deletePlayer(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(PLAYER_TABLE,COLUMN_ID + " = " + id, null);
        if (result == -1){
            db.close();
            return false;
        }
        else
        {
            db.close();
            return true;
        }
    }

    public ArrayList<PlayerModel> readPlayers(){

        ArrayList<PlayerModel> returnList = new ArrayList<>();

        String query = "SELECT * FROM " + PLAYER_TABLE + " ORDER BY " + COLUMN_PLAYER_SCORE + " DESC LIMIT 5";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                int _id = cursor.getInt(0);
                String playerName = cursor.getString(1);
                int playerScore = cursor.getInt(2);

                try {
                    PlayerModel newPlayer = new PlayerModel(_id,
                            playerName, playerScore);
                    returnList.add(newPlayer);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }while (cursor.moveToNext());
        }else{

        }

        cursor.close();
        db.close();
        return returnList;
    }
}
