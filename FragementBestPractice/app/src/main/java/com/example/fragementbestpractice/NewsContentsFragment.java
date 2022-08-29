package com.example.fragementbestpractice;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class NewsContentsFragment extends Fragment {

    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.news_content_frag,container,false);
        return view;
    }

    public void refresh(String news_title,String news_content){
        View visibility_layout=view.findViewById(R.id.visibility_layout);
        visibility_layout.setVisibility(View.VISIBLE);

        TextView textView_title=view.findViewById(R.id.news_title);
        TextView textView_content=view.findViewById(R.id.news_content);

        textView_title.setText(news_title);
        textView_content.setText(news_content);
    }
}
