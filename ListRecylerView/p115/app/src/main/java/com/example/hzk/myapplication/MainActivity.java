package com.example.hzk.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * ListView的简单使用
 *
 */
public class MainActivity extends AppCompatActivity {
    private String[] data  = {"Apple","Banana","Orange","Pear","Apple","Banana","Orange","Pear",
            "Apple","Banana","Orange","Pear","Apple","Banana","Orange","Pear","Apple","Banana","Orange","Pear"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,data);
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);
    }
}
