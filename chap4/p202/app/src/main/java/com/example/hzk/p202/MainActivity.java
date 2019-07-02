package com.example.hzk.p202;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * 1.继承View重写onDraw方法，支持wrap_content（指定一个wrap_content模式下的高宽）和padding(在OnDraw方法中计算),
 * 还要对外提供自定义属性。
 */
public class MainActivity extends AppCompatActivity {

    private Button btn_click;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
