package com.example.hzk.myapplication;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

/***
 * 功能：异常情况下的生命周期分析。
 * 资源相关的系统配置发生改变导致Activity被杀死并重新创建。譬如：旋转屏幕后数据的保存和恢复。
 */
public class MainActivity extends AppCompatActivity {
    private static String TAG = "MainActivity";
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.edt_test);
        if (savedInstanceState != null) {
            String test = savedInstanceState.getString("extra_test");
            Log.d(TAG, "[OnCreate]Restore onCreate: " + test);
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.d(TAG, "onConfigurationChanged,newOrientation: " + newConfig.orientation);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState: ");
        outState.putString("extra_test", "testing");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String test = savedInstanceState.getString("extra_test");
        Log.d(TAG, "[onRestoreInstanceState]Restore extra_test " + test);
    }
}
