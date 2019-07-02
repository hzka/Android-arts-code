package com.example.hzk.myapplication;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_click;
    private TextView tv_content;
    private ThreadLocal<Boolean> mbooleanThreadLocal = new ThreadLocal<>();
    private final String TAG = "MainActivity";
    //整型常量UPDATE_TEXT用来更新TextView这个操作
    public static final int UPDATE_TEXT = 1;
    //新建Handler对象，并重写它的handleMessage方法，在这里对具体的Message进行处理。
    private Handler handler = new Handler(){
        //handleMessage方法中的代码是在主线程中运行的，在这里进行UI操作。
        @Override
        public void handleMessage(Message msg) {
            //如果发现Message的what字段等于UPDATE_TEXT，修改TextView的内容
            switch (msg.what){
                case UPDATE_TEXT:
                    //在这里可以进行UI操作
                    tv_content.setText("Nice to meet u");
                    break;
                default:
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_click = (Button) findViewById(R.id.btn_click);
        tv_content = (TextView) findViewById(R.id.tv_01);
        btn_click.setOnClickListener(this);
        mbooleanThreadLocal.set(true);
        Log.d(TAG, "[Thread:mbooleanThreadLocal=]"+mbooleanThreadLocal.get());

        new Thread(new Runnable() {
            @Override
            public void run() {
                mbooleanThreadLocal.set(false);
                Log.d(TAG, "[Thread1:mbooleanThreadLocal=]"+mbooleanThreadLocal.get());
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
//                mbooleanThreadLocal.set(false);
                Log.d(TAG, "[Thread2:mbooleanThreadLocal=]"+mbooleanThreadLocal.get());
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();//为当前线程创建一个Looper
                Handler handler01 = new Handler();
                Log.d(TAG, "run: "+getMainLooper());//可以在任何地方获取到主线程的Looper
                Looper.loop();//开启消息循环
            }
        }).start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_click:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //创建一个Message对象，并在这里对具体的Message进行处理
                        Message message = new Message();
                        message.what = UPDATE_TEXT;
                        handler.sendMessage(message);//将Message对象传递出去。
                    }
                }).start();
        }
    }
}