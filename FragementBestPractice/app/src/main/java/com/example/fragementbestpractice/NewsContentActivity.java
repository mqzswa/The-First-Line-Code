package com.example.fragementbestpractice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class NewsContentActivity extends AppCompatActivity {

    public static void actionStart(String title,String content,Context context){
        Intent intent=new Intent(context,NewsContentActivity.class);
        intent.putExtra("Title",title);
        intent.putExtra("Content",content);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_content);

        String news_title=getIntent().getStringExtra("Title");
        String news_content=getIntent().getStringExtra("Content");

        NewsContentsFragment newsContentsFragment= (NewsContentsFragment) getSupportFragmentManager().findFragmentById(R.id.news_content_fragment);

        newsContentsFragment.refresh(news_title,news_content);

    }
}