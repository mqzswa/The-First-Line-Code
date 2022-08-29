package com.example.uiwidgettest;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button= findViewById(R.id.button1);
        EditText editText= findViewById(R.id.edit_text);
        ImageView img= findViewById(R.id.image_view);
        ProgressBar processbar= findViewById(R.id.process_bar);
        button.setOnClickListener(view -> {
            String data=editText.getText().toString();
            Toast.makeText(MainActivity.this, data, Toast.LENGTH_SHORT).show();
            img.setImageResource(R.drawable.img2);
//                if(processbar.getVisibility()==View.GONE){
//                    processbar.setVisibility(View.VISIBLE);
//                }else{
//                    processbar.setVisibility(View.GONE);
//                }
            int process=processbar.getProgress();
            process+=10;
            processbar.setProgress(process);

            AlertDialog.Builder dialog=new AlertDialog.Builder (MainActivity.this);
            dialog.setCancelable(false);
            dialog.setMessage("This is dialog");
            dialog.setTitle("warning");
            dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    MainActivity.this.finish();
                }
            });
            dialog.setNegativeButton("Cancel", (dialog1, which) -> {

            });

            dialog.show();
        });
        ProgressDialog progressDialog=new ProgressDialog(MainActivity.this);
        progressDialog.setTitle("This is progressDialog");
        progressDialog.setMessage("Loading………");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }
}