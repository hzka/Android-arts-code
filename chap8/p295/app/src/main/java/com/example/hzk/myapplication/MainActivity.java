package com.example.hzk.myapplication;

import android.graphics.PixelFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button btn_click = new Button(this);
        btn_click.setText("button");

        final WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        final WindowManager.LayoutParams layout = new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT
                , WindowManager.LayoutParams.WRAP_CONTENT, 0, 0, PixelFormat.TRANSLUCENT);
        layout.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED;
        //Flag参数表示window的属性，通过这些选项控制Window的显示特性：
        //1.FLAG_NOT_FOCUSABLE:表示窗口不需要获取焦点，也不需要接收各种事件，这属性会同时启动FLAG_NOT_TOUCH_MODAL，最终事件会传递给下层的具体焦点的window
        //2.FLAG_NOT_TOUCH_MODAL:系统会将当前window区域以外的单击事件传递给底层的Window，当前的Window区域以内的单机事件自己处理，这个标记很重要，一般来说都需要开启，否则其他windows无法接受到点击事件。
        //3.FLAG_SHOW_WHEN_LOCKE:开启这个属性可以让window显示在锁屏上
        layout.gravity = Gravity.CENTER;
        layout.type = WindowManager.LayoutParams.TYPE_PHONE;
        layout.x = 100;
        layout.y = 300;
        wm.addView(btn_click, layout);



        btn_click.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
//                float DistanceX = event.getRawX() - btn_click.getLeft();
//                float DistanceY = event.getRawY() - btn_click.getTop();
//                int rawx = (int) (event.getRawX() - DistanceX);
//                int rawy = (int) (event.getRawY() - DistanceY);
                switch (event.getAction()) {
                    case MotionEvent.ACTION_MOVE: {
                        int rawx = (int) (event.getX());//getRawX
                        int rawy = (int) (event.getY());//getRawY
                        layout.x = rawx;
                        layout.y = rawy;
                        wm.updateViewLayout(btn_click, layout);
                        break;
                    }
                    default:
                        break;
                }
                return false;
            }
        });
    }
}
