package com.example.uilayouttest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class ThirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_layout);

        Button button3=(Button) findViewById(R.id.button_3_3);
        button3.setOnClickListener(v->{
            Intent intent=new Intent(ThirdActivity.this,FourthActivity.class);
            startActivity(intent);
        });
    }
}