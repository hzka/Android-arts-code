package com.example.hzk.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.testset01,R.anim.testset01);
    }
}
