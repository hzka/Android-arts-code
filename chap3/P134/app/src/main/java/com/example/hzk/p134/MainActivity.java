package com.example.hzk.p134;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import com.nineoldandroids.view.ViewHelper;

/***
 * Function：
 * 1.实现一个跟手滑动的效果，自定义View，拖动它可以让他在整个屏幕上随意移动；
 * 2.使用延时策略实现弹性滑动效果。P139。
 */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "TestButton";
    private int mScaledTouchSlop;
    // 分别记录上次滑动的坐标
    private int mLastX = 0;
    private int mLastY = 0;
    //下面采用Handler来做个示例,在大约1000ms内将View的内容向左移动了100像素，
    private static final int MESSAGE_SCOLL_TO = 1;
    private static final int FRAME_COUNT = 30;
    private static final int DELAYED_TIME = 33;
    private int count =1;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case MESSAGE_SCOLL_TO:
                    count++;
                    if(count<=FRAME_COUNT){
                        float fraction = count/(float)FRAME_COUNT;
                        int scrollx = (int)fraction*100;
                        new TestButton(getApplicationContext()).scrollTo(scrollx,0);
                        handler.sendEmptyMessageDelayed(MESSAGE_SCOLL_TO,DELAYED_TIME);
                    }
                    break;
            }
        }
    };


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getRawX();
        int y = (int) event.getRawY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:{
                int deltaX = x - mLastX;
                int deltaY = y - mLastY;
                Log.d(TAG, "move, deltaX:" + deltaX + " deltaY:" + deltaY);
//                int translationX = (int) ViewHelper.getTranslationX(this) + deltaX;
//                int translationY = (int)ViewHelper.getTranslationY(this) + deltaY;
//                ViewHelper.setTranslationX(this, translationX);
//                ViewHelper.setTranslationY(this, translationY);
                break;
            }
        }
        return true;
    }
}
