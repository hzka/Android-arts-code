package com.example.hzk.fc341;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_click;
    private TextView tv_content;
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
        tv_content = (TextView) findViewById(R.id.text);
        btn_click.setOnClickListener(this);
//        //启动刚才定义的线程
//        new MyThread().run();
//
//        //启动刚才定义的实现Runnable接口的线程。
//        MyThread01 myThread01 = new MyThread01();
//        //Thread构造函数接受一个Runnable参数并传至Thread构造函数中。
//        new Thread(myThread01).start();
//        //匿名内部类实现并运行一个线程
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                //处理耗时具体逻辑
//            }
//        }).start();
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

    //定义一个线程，继承自Thread类并实现其run方法
    class MyThread extends Thread{
        @Override
        public void run() {
            //处理具体耗时逻辑。
        }
    }
    //实现Runnable接口会导致较低的耦合性，
    class MyThread01 implements Runnable{
        @Override
        public void run() {
            //处理具体逻辑
        }
    }
}
