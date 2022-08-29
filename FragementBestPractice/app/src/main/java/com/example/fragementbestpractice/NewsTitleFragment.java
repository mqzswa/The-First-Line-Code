package com.example.fragementbestpractice;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class NewsTitleFragment extends Fragment {

    private boolean flags=false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_title_frag,container,false);
        RecyclerView recyclerView=view.findViewById(R.id.news_title_recycler);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        NewsAdapter newsAdapter=new NewsAdapter(init_list());
        recyclerView.setAdapter(newsAdapter);
        return view;
    }

    public List<News> init_list(){
        List<News>list=new ArrayList<>();

        for(int i=0;i<50;++i)
        {
            News news=new News();
            news.setTitle("This is Title:"+i);
            news.setContent(getRandomLengthContent("This is new Content:"+i+'.'));
            list.add(news);
        }

        return list;
    }

    private String getRandomLengthContent(String s) {
        Random random=new Random();
        int length=random.nextInt(20)+1;
        StringBuilder stringBuilder=new StringBuilder();
        for(int i=0;i<length;++i)
        {
            stringBuilder.append(s);
        }
        return stringBuilder.toString();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        flags= requireActivity().findViewById(R.id.news_content) != null;
    }

    class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder>{

        private final List<News>newsList;

        class ViewHolder extends RecyclerView.ViewHolder{
            TextView textView;

            public ViewHolder(View view){
                super(view);
                textView=view.findViewById(R.id.news_title);
            }
        }

        public NewsAdapter(List<News>list){
            newsList=list;
        }

        @NonNull
        @Override
        public NewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item,parent,false);
            ViewHolder viewHolder=new ViewHolder(view);

            view.setOnClickListener(v -> {
                News news=newsList.get(viewHolder.getAbsoluteAdapterPosition());
                if(flags)
                {
                    NewsContentsFragment newsContentsFragment= (NewsContentsFragment) getFragmentManager().findFragmentById(R.id.news_content_fragment);
                    //assert newsContentsFragment != null;
                    newsContentsFragment.refresh(news.getTitle(),news.getContent());
                }
                else
                {
                    NewsContentActivity.actionStart(news.getTitle(),news.getContent(),getActivity());
                }
            });
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull NewsAdapter.ViewHolder holder, int position) {
            News news=newsList.get(position);
            holder.textView.setText(news.getTitle());
        }

        @Override
        public int getItemCount() {
            return newsList.size();
        }
    }
}
