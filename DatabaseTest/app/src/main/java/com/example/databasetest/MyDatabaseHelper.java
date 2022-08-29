package com.example.databasetest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    public static final String CREATE_BOOK="create table book ( id INTEGER primary key autoincrement,author varchar(20),price float,pages int,name varchar(20));";
    public Context context;

    public static final String CREATE_CATEGORY="create table category ( id integer primary key autoincrement,category_name varchar(20),category_code integer);";

    public MyDatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("Database:", CREATE_BOOK);
        db.execSQL(CREATE_BOOK);
        db.execSQL(CREATE_CATEGORY);
        //Toast.makeText(context, "database create success", Toast.LENGTH_SHORT).show();
        
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists book");
        db.execSQL("drop table if exists category");
        onCreate(db);
    }
}
