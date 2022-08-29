package com.example.filepersistencetest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    EditText editText=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText=findViewById(R.id.edittext);

        String data=load();
        editText.setText(data);
        editText.setSelection(data.length());
        Toast.makeText(this, "loading success!", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        String data= editText.getText().toString();
        save(data);
    }

    public void save(String data){
        FileOutputStream out=null;
        BufferedWriter writer = null;
        try {
            out=openFileOutput("data.txt",MODE_PRIVATE);
            writer=new BufferedWriter(new OutputStreamWriter(out));
            writer.write(data);
        }catch (IOException e){
            e.printStackTrace();
        }
        finally {
            try {
                if(writer!=null){
                    writer.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public String load(){
        FileInputStream in=null;
        BufferedReader reader=null;
        StringBuilder data=new StringBuilder();
        String line;
        try {
            in=openFileInput("data.txt");
            reader=new BufferedReader(new InputStreamReader(in));
            while((line=reader.readLine())!=null){
                data.append(line);
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                if(reader!=null){
                    reader.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return data.toString();
    }
}