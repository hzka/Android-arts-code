package com.example.hzk.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/***
 * 功能:主要用来测试生命周期，以及回答Activty中假设当前Activity为A，
 * 如果用户打开了一个新的Activity为B，那么B的onResume和A的onPause谁先执行的问题？
 */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "MainActivity onCreate: ");

        Button btn_click = (Button) findViewById(R.id.btn_click);;
        btn_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent01 = new Intent(MainActivity.this,SecondActivity.class);
                startActivity(intent01);
            }
        });

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "MainActivity onRestart: ");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "MainActivity onStart: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "MainActivity onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "MainActivity onStop: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "MainActivity onResume: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "MainActivity onDestroy: ");
    }
}
