package com.example.uilayouttest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layout);

        Button button2=(Button)findViewById(R.id.button_send);
        button2.setOnClickListener(v->{
            Intent intent = new Intent(SecondActivity.this,ThirdActivity.class);
            startActivity(intent);
        });
    }
}