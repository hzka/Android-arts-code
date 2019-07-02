package com.example.hzk.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Fruit> fruitList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFruits();//初始化水果数据
        FruitAdapter adapter = new FruitAdapter(MainActivity.this, R.layout.fruit_item, fruitList);
        ListView listview = (ListView) findViewById(R.id.list_view);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fruit fruit = fruitList.get(position);
                Toast.makeText(MainActivity.this,fruit.getName(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initFruits() {
        for (int i = 0; i < 5; i++) {
            Fruit apple = new Fruit("Apple", R.mipmap.ic_launcher);
            fruitList.add(apple);
            Fruit banana = new Fruit("banana", R.mipmap.ic_launcher_round);
            fruitList.add(banana);
            Fruit orange = new Fruit("orange", R.mipmap.ic_launcher);
            fruitList.add(orange);
            Fruit grape = new Fruit("grpae", R.mipmap.ic_launcher_round);
            fruitList.add(grape);
        }
    }
}
