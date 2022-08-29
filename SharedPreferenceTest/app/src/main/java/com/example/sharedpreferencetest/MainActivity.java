package com.example.sharedpreferencetest;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button=findViewById(R.id.button_write);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor=getSharedPreferences("data",MODE_PRIVATE).edit();
                editor.putBoolean("First",true);
                editor.putBoolean("Second",false);
                editor.putInt("Third",3);
                editor.putString("Fourth","Hello world");

                editor.apply();;
            }
        });

        Button button2=findViewById(R.id.button_read);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data;
                SharedPreferences sharedPreferences=getSharedPreferences("data",MODE_PRIVATE);
                data=sharedPreferences.getString("Fourth","null");
                Log.d("SharedPreferences:", data);
            }
        });
    }
}