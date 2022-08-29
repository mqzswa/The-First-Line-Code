package com.example.databasetest;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyDatabaseHelper myDatabaseHelper=new MyDatabaseHelper(this,"BookStore.db",null,2);

        Button button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDatabaseHelper.getWritableDatabase();
            }
        });

        Button button2=findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues contentValues=new ContentValues();
                contentValues.put("name","The first line code");
                contentValues.put("author","Guo_lin");
                contentValues.put("price",80.50);
                contentValues.put("pages",248);
                SQLiteDatabase sqLiteDatabase=myDatabaseHelper.getWritableDatabase();
                sqLiteDatabase.insert("Book",null,contentValues);

                sqLiteDatabase.execSQL("insert into book (name,author,price,pages) values (?,?,?,?)",new String[]{"Hello world","Zhang san","39.50","328"});
            }
        });

        Button button3=findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase sqLiteDatabase=myDatabaseHelper.getWritableDatabase();
                ContentValues contentValues=new ContentValues();
                contentValues.put("price",50.90);
                sqLiteDatabase.update("book",contentValues,"name=?",new String[]{"The first line code"});
            }
        });

        Button button4=findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase sqLiteDatabase=myDatabaseHelper.getWritableDatabase();
                ContentValues contentValues=new ContentValues();
                sqLiteDatabase.delete("book","pages>?",new String[]{"200"});
            }
        });

        Button button5=findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase sqLiteDatabase=myDatabaseHelper.getWritableDatabase();
                ContentValues contentValues=new ContentValues();
                Cursor cursor=sqLiteDatabase.query("book",null,null,null,null,null,null);

                if(cursor.moveToFirst()) {
                    do {
                        @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("name"));
                        @SuppressLint("Range") String author = cursor.getString(cursor.getColumnIndex("author"));
                        @SuppressLint("Range") int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                        @SuppressLint("Range") float price = cursor.getFloat(cursor.getColumnIndex("price"));

                        Log.d("name is:", name);
                        Log.d("author is:", author);
                        Log.d("price is:", String.valueOf(price));
                        Log.d("pages is:", String.valueOf(pages));
                    } while (cursor.moveToNext());
                }
                cursor.close();
            }
        });
    }
}