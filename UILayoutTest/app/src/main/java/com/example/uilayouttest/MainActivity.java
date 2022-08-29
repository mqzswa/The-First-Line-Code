package com.example.uilayouttest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button1=(Button) findViewById(R.id.button_1);
        button1.setOnClickListener( v->{
            Intent intent=new Intent(MainActivity.this,SecondActivity.class);
            startActivity(intent);
        });
    }
}