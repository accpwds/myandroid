package com.example.android;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.android.object.Fruit;

import java.util.ArrayList;
import java.util.List;

public class FruitActivity2 extends BaseActivity {

    private List<Fruit> fruitList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit2);
        initFruits();
        //自定义适配器2
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //layoutManager.setOrientation(0);
        recyclerView.setLayoutManager(layoutManager);
        FruitAdapter2 fruitAdapter2 = new FruitAdapter2(fruitList);
        recyclerView.setAdapter(fruitAdapter2);
    }

    private void initFruits(){
        for (int i=0;i<2;i++){
            Fruit apple = new Fruit("Apple",R.mipmap.ic_launcher);
            fruitList.add(apple);
            Fruit banana = new Fruit("Banana",R.mipmap.ic_launcher_round);
            fruitList.add(banana);
            Fruit orange = new Fruit("Orange",R.mipmap.ic_launcher);
            fruitList.add(orange);
            Fruit watermelon = new Fruit("Watermelon",R.mipmap.ic_launcher_round);
            fruitList.add(watermelon);
            Fruit pear = new Fruit("Pear",R.mipmap.ic_launcher);
            fruitList.add(pear);
            Fruit grape = new Fruit("Grape",R.mipmap.ic_launcher_round);
            fruitList.add(grape);
            Fruit pineapple = new Fruit("Pineapple",R.mipmap.ic_launcher);
            fruitList.add(pineapple);
            Fruit strawberry = new Fruit("Strawberry",R.mipmap.ic_launcher_round);
            fruitList.add(strawberry);
            Fruit cherry = new Fruit("Cherry",R.mipmap.ic_launcher);
            fruitList.add(cherry);
            Fruit mango = new Fruit("Mango",R.mipmap.ic_launcher_round);
            fruitList.add(mango);
        }
    }
}
