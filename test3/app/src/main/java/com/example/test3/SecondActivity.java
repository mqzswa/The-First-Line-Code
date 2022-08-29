package com.example.test3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends BaseActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layout);

        Log.d("SecondActivity",this.toString());
        Log.d("Second","Task id is"+getTaskId());
        Button button2=(Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent=new Intent(SecondActivity.this,ThirdActivity.class);
//                startActivity(intent);
                ThirdActivity.actionActivity(SecondActivity.this,"hello","hi");
            }
        });
    }
}