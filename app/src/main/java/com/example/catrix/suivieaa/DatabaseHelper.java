package com.example.catrix.suivieaa;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(Context context){
        super(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION);
        doDBCheck();
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Constants.DATABASE_CREATE_TABLE_VISITES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    private void doDBCheck(){
        try{
            File file = new File("data/data/com.example.catrix.suivieaa/databases/"+Constants.DATABASE_NAME);
            file.delete();
        }catch (Exception e){
            Log.e("JLE", "'La DB n'existe pas");
        }
    }
}
