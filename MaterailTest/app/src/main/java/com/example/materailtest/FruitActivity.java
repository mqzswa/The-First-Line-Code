package com.example.materailtest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.CollapsingToolbarLayout;

public class FruitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit);

        Intent intent=getIntent();
        String fruit_name=intent.getStringExtra("fruit_name");
        int fruit_id=intent.getIntExtra("fruit_id",0);

        Toolbar toolbar=findViewById(R.id.coll_toolbar);
        CollapsingToolbarLayout collapsingToolbarLayout=findViewById(R.id.coll_layout);

        ImageView imageView=findViewById(R.id.coll_image);
        TextView textView=findViewById(R.id.coll_text);
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        collapsingToolbarLayout.setTitle(fruit_name);
        Glide.with(this).load(fruit_id).into(imageView);
        String context=Getcontext(fruit_name);
        textView.setText(context);
    }

    private String Getcontext(String fruit_name) {
        StringBuilder stringBuilder=new StringBuilder();
        for (int i=0;i<500;++i){
            stringBuilder.append(fruit_name);
        }
        return stringBuilder.toString();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}