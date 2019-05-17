package com.example.alaaismail.tasksolution.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBconnection extends SQLiteOpenHelper {

    public static final int Version = 1;
    public static final String DBName = "information";

    public DBconnection(Context context){
        super(context,DBName,null,Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not EXISTS employee(id INTEGER PRIMARY KEY , name TEXT , email TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS employee");
        onCreate(db);
    }

    public void insertInfo (String name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        db.insert("employee",null,contentValues);
    }

    public ArrayList getAllrecord(){
        ArrayList array_list = new ArrayList();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM employee",null);
        res.moveToFirst();
        while (res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex("name")));
            res.moveToNext();
        }

        return array_list;
    }
}
