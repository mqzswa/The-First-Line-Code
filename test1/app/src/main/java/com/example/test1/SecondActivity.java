package com.example.test1;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layout);

        Intent intent=getIntent();
        String data=intent.getStringExtra("data");
        Toast.makeText(SecondActivity.this,data,Toast.LENGTH_SHORT).show();

        ActivityResultLauncher<Intent> intentActivityResultLauncher= registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                switch (result.getResultCode()){
                    case RESULT_OK:
                        Toast.makeText(SecondActivity.this, result.getData().getStringExtra("return_data"), Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Log.d(TAG, "return error");
                }
            }
        });

        Button button2=(Button) findViewById(R.id.button_2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SecondActivity.this,ThirdActivity.class);
                intentActivityResultLauncher.launch(intent);
            }
        });
    }
}