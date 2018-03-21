package com.example.android.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.example.android.adapter.FruitAdapter3;
import com.example.android.R;
import com.example.android.object.Fruit;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FruitActivity3 extends BaseActivity {

    private List<Fruit> fruitList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit2);
        initFruits();
        //自定义适配器2
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        //瀑布流數據顯示
        StaggeredGridLayoutManager staggeredGridLayoutManager = new
                StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        //layoutManager.setOrientation(0);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        FruitAdapter3 fruitAdapter3 = new FruitAdapter3(fruitList);
        recyclerView.setAdapter(fruitAdapter3);
    }

    private void initFruits(){
        for (int i=0;i<2;i++){
            Fruit apple = new Fruit(getRandomName("Apple"),"Apple",R.mipmap.ic_launcher);
            fruitList.add(apple);
            Fruit banana = new Fruit(getRandomName("Banana"),"Banana",R.mipmap.ic_launcher_round);
            fruitList.add(banana);
            Fruit orange = new Fruit(getRandomName("Orange"),"Orange",R.mipmap.ic_launcher);
            fruitList.add(orange);
            Fruit watermelon = new Fruit(getRandomName("Watermelon"),"Watermelon",R.mipmap.ic_launcher_round);
            fruitList.add(watermelon);
            Fruit pear = new Fruit(getRandomName("Pear"),"Pear",R.mipmap.ic_launcher);
            fruitList.add(pear);
            Fruit grape = new Fruit(getRandomName("Grape"),"Grape",R.mipmap.ic_launcher_round);
            fruitList.add(grape);
            Fruit pineapple = new Fruit(getRandomName("Pineapple"),"Pineapple",R.mipmap.ic_launcher);
            fruitList.add(pineapple);
            Fruit strawberry = new Fruit(getRandomName("Strawberry"),"Strawberry",R.mipmap.ic_launcher_round);
            fruitList.add(strawberry);
            Fruit cherry = new Fruit(getRandomName("Cherry"),"Cherry",R.mipmap.ic_launcher);
            fruitList.add(cherry);
            Fruit mango = new Fruit(getRandomName("Mango"),"Mango",R.mipmap.ic_launcher_round);
            fruitList.add(mango);
        }
    }

    private String getRandomName(String name){
        Random random = new Random();
        int length = random.nextInt(20) + 1;
        StringBuffer stringBuffer = new StringBuffer();
        for (int i=0;i<length;i++){
            stringBuffer.append(name);
        }
        return stringBuffer.toString();
    }
}
