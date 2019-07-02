package com.example.hzk.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class SecondActivity extends AppCompatActivity {
    private static String TAG = "SecondActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Log.d(TAG, "SecondActivity onCreate: ");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "SecondActivity onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "SecondActivity onResume: ");
    }
}
