package com.example.listviewtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String[] data={"Apple","Pear","watermelon","lemon","strawberry","banana","peach",
            "cherry","pomegranate","chestnut","hazelnut","grape","pineapple","orange"};

    private List<Fruit>list=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        ArrayAdapter<String>adapter=new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1,data);
//        ListView listView=findViewById(R.id.listview);
//        listView.setAdapter(adapter);

        initFruit();
        FruitAdapter adapter=new FruitAdapter(MainActivity.this, R.layout.fruit_item,list);
        ListView listView=findViewById(R.id.listview);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fruit fruit=list.get(position);
                Toast.makeText(MainActivity.this, fruit.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void initFruit(){
        for(int i=0;i<2;++i){
            for(int j=0;j< data.length;++j){
                Fruit fruit=new Fruit(data[j],R.drawable.img);
                list.add(fruit);
            }
        }
    }
}