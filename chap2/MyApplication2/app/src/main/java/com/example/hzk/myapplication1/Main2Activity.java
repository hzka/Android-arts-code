package com.example.hzk.myapplication1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class Main2Activity extends AppCompatActivity {
    public static final String TAG = "Main2Activity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Person person = (Person) getIntent().getSerializableExtra("person_data");
        Log.i(TAG,  "Main2Activity:"+person.getName());
        Log.i(TAG, "Main2Activity: "+person.getAge());
    }
}
