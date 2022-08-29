package com.example.recyclerviewtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private String[] data={"Apple","Pear","watermelon","lemon","strawberry","banana","peach",
            "cherry","pomegranate","chestnut","hazelnut","grape","pineapple","orange"};

    private List<Fruit>fruitList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFruit();
        FruitAdapter fruitAdapter=new FruitAdapter(fruitList);
        RecyclerView recyclerView=findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(fruitAdapter);

    }


    public void initFruit(){
        for(int i=0;i<2;++i){
            for(int j=0;j< data.length;++j){
                Fruit fruit=new Fruit(data[j],R.drawable.img);
                fruitList.add(fruit);
            }
        }
    }
}