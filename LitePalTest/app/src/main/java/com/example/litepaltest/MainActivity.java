package com.example.litepaltest;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.litepal.LitePal;
import org.litepal.tablemanager.Connector;

import java.sql.Connection;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Connector.getDatabase();
            }
        });

        Button button2=findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book=new Book();

                book.setAuthor("Zhang san");
                book.setName("Hello World");
                book.setPages(600);
                book.setPress("人民邮电出版社");
                book.setPrice(87.90);

                book.save();
            }
        });

        Button button3=findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book=new Book();
                book.setPrice(45.23);
                book.setPress("人民教育出版社");
                book.updateAll("name=? and author = ?","Hello World","Zhang san");
            }
        });

        Button button4=findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LitePal.deleteAll(Book.class,"price < ?","60");
            }
        });

        Button button5=findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                List<Book>bookList=LitePal.findAll(Book.class);
//                Log.d("name is:",bookList.get(0).getName());

                List<Book>bookList=LitePal.select("name","author","price").where("pages>?","300").order("price").limit(10).offset(0).find(Book.class);
                Log.d("name is:", bookList.get(0).getName());

                //Cursor cursor=LitePal.findBySQL("");
            }
        });
    }
}