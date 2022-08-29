package com.example.uilayouttest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class FourthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fourth_layout);

        Button button4=findViewById(R.id.button_4_1);
        button4.setOnClickListener(v->{
            Intent intent=new Intent(FourthActivity.this,FifthActivity.class);
            startActivity(intent);
        });
    }
}