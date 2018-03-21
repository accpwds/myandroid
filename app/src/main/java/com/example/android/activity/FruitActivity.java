package com.example.android.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.android.adapter.FruitAdapter;
import com.example.android.R;
import com.example.android.object.Fruit;

import java.util.ArrayList;
import java.util.List;

public class FruitActivity extends AppCompatActivity {

    private List<Fruit> fruitList = new ArrayList<>();

    private String[] data = {"Apple", "Banana", "Orange", "Watermelon", "Pear", "Grape",
            "Pineapple", "Strawberry", "Cherry", "Mango", "Apple", "Banana", "Orange",
            "Watermelon", "Pear", "Grape", "Pineapple", "Strawberry", "Cherry", "Mango"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit);
        //调用系统默认适配器
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(FruitActivity.this,
//                android.R.layout.simple_expandable_list_item_1, data);
        initFruits();
        //自定义适配器
        FruitAdapter adapter = new FruitAdapter(FruitActivity.this,R.layout.fruit_item,fruitList);
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fruit fruit = fruitList.get(position);
                Toast.makeText(FruitActivity.this,fruit.getName(),Toast.LENGTH_SHORT).show();
            }
        });
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
