package com.example.hzk.p122;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private List<Fruit> fruitList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化所有水果数据
        initFruits();
        //获取RecyclerView实例
        RecyclerView recyclerview = (RecyclerView) findViewById(R.id.recycler_01);
        //创建一个LinearLayoutManager对象
//        LinearLayoutManager layoutmanager = new LinearLayoutManager(this);
        StaggeredGridLayoutManager layoutmanager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        //设置布局的排列方向，默认是纵向排列的，使用LinearLayoutManager.HORIZONTAL让其布局变为横向排列。
        //RecyclerView将布局排列交给LayoutManager去管理，而非ListView自身，LayoutManager提供了一系列可扩展的布局排列接口
//        layoutmanager.setOrientation(LinearLayoutManager.HORIZONTAL);
        //将线性布局设置进去
        recyclerview.setLayoutManager(layoutmanager);
        //将水果数据传入FruitAdapter构造函数中
        FruitAdapter adapter = new FruitAdapter(fruitList);
        //完成适配器设置
        recyclerview.setAdapter(adapter);
    }

    private void initFruits() {
        for (int i = 0; i < 5; i++) {
            Fruit apple = new Fruit(getRandomLengthName("Apple"), R.mipmap.ic_launcher);
            fruitList.add(apple);
            Fruit banana = new Fruit(getRandomLengthName("banana"), R.mipmap.ic_launcher_round);
            fruitList.add(banana);
            Fruit orange = new Fruit(getRandomLengthName("orange"), R.mipmap.ic_launcher);
            fruitList.add(orange);
            Fruit grape = new Fruit(getRandomLengthName("grpae"), R.mipmap.ic_launcher_round);
            fruitList.add(grape);
        }
    }

    private String getRandomLengthName(String name) {
        Random random = new Random();
        int length = random.nextInt(20)+1;
        StringBuilder builder = new StringBuilder();
        for(int i =0;i<length;i++){
            builder.append(name);
        }
        return builder.toString();
    }
}
