package com.example.uilayouttest2;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class TitleLayout extends LinearLayout {
    public TitleLayout(Context context, AttributeSet attrs){
        super(context,attrs);
        LayoutInflater.from(context).inflate(R.layout.title,this);
        Button button1=findViewById(R.id.title_back);
        Button button2=findViewById(R.id.title_edit);

        button1.setOnClickListener(v->{
            ((Activity)getContext()).finish();
        });

        button2.setOnClickListener(v->{
            Toast.makeText(getContext(),"you click edit",Toast.LENGTH_SHORT).show();
        });
    }
}
