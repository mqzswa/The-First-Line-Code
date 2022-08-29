package com.example.recyclerviewtest;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {
    private List<Fruit>list;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fruit_item,viewGroup,false);
        final ViewHolder viewHolder=new ViewHolder(view);
        viewHolder.fruit_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position=viewHolder.getAdapterPosition();
                Fruit fruit=list.get(position);
                Toast.makeText(v.getContext(), "you click "+fruit.getName(), Toast.LENGTH_SHORT).show();
            }
        });


        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position=viewHolder.getAdapterPosition();
                Fruit fruit=list.get(position);
                Toast.makeText(v.getContext(), "you click "+fruit.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Fruit fruit=list.get(i);
        viewHolder.imageView.setImageResource(fruit.getId());
        viewHolder.textView.setText(fruit.getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;
        View fruit_view;

        public ViewHolder(View view){
            super(view);
            fruit_view=view;
            imageView=view.findViewById(R.id.image);
            textView=view.findViewById(R.id.text_name);
        }
    }

    public  FruitAdapter(List<Fruit>object){
        list=object;
    }
}
