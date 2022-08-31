package com.example.materailtest;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {

    private Context context;
    private List<Fruit>fruitList;

    static class ViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;
        ImageView imageView;
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.image_view);
            textView=itemView.findViewById(R.id.text_view);
            cardView= (CardView) itemView;
        }

    }

    public FruitAdapter(List<Fruit> fruitList) {
        this.fruitList = fruitList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(context==null){
            context=parent.getContext();
        }
        View view= LayoutInflater.from(context).inflate(R.layout.fruit_item,parent,false);

        ViewHolder viewHolder=new ViewHolder(view);
//        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int position=viewHolder.getAbsoluteAdapterPosition();
//                Fruit fruit=fruitList.get(position);
//                Toast.makeText(context, "you clicked "+fruit.getFruit_name(), Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int position=viewHolder.getAbsoluteAdapterPosition();
//                Fruit fruit=fruitList.get(position);
//                Toast.makeText(context, "you clicked "+fruit.getFruit_name(), Toast.LENGTH_SHORT).show();
//            }
//        });

        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAbsoluteAdapterPosition();
                Fruit fruit = fruitList.get(position);
                Intent intent=new Intent(context,FruitActivity.class);
                intent.putExtra("fruit_name",fruit.getFruit_name());
                intent.putExtra("fruit_id",fruit.getFruit_id());
                context.startActivity(intent);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Fruit fruit=fruitList.get(position);
        holder.textView.setText(fruit.getFruit_name());
        Glide.with(context).load(fruit.getFruit_id()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return fruitList.size();
    }
}
