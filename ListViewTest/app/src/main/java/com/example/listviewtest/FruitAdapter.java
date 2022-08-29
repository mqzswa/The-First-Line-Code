package com.example.listviewtest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class FruitAdapter extends ArrayAdapter<Fruit> {

    private int resourceid;

    FruitAdapter(Context context, int resourceId, List<Fruit> object){
        super(context,resourceId,object);
        resourceid=resourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Fruit fruit=getItem(position);

        View view;
        if(convertView==null) {
            view = LayoutInflater.from(getContext()).inflate(resourceid, parent, false);
        }
        else{
            view=convertView;
        }
        ImageView imageView=view.findViewById(R.id.image);
        TextView textView=view.findViewById(R.id.text_name);
        imageView.setImageResource(fruit.getId());
        textView.setText(fruit.getName());
        return view;
    }
}
