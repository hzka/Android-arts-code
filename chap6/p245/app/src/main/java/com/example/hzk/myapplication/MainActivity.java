package com.example.hzk.myapplication;
/**
 * 功能:1.九大Drawable。
 * 2.自定义Drawable。
 */

import android.graphics.Canvas;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.ScaleDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tv;
    private ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.tv);
        iv= (ImageView) findViewById(R.id.iv);
//        TransitionDrawable drawable = (TransitionDrawable) tv.getBackground();
//        drawable.startTransition(1000);

//        ScaleDrawable testdrawable = (ScaleDrawable) tv.getBackground();
//        testdrawable.setLevel(1);
        ClipDrawable cp = (ClipDrawable) iv.getDrawable();
        cp.setLevel(2000);
    }
}
