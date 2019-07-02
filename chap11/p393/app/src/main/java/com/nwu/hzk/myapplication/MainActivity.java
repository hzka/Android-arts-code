package com.nwu.hzk.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private Button btn_click,btn_click02;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_click = (Button) findViewById(R.id.btn_click);
        btn_click02 = (Button) findViewById(R.id.btn_click02);
        btn_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //AsyncTask
//                new MyAsyncTask("AsynacTask#1").execute("");
//                new MyAsyncTask("AsynacTask#2").execute("");
//                new MyAsyncTask("AsynacTask#3").execute("");
//                new MyAsyncTask("AsynacTask#4").execute("");
//                new MyAsyncTask("AsynacTask#5").execute("");
//                new MyAsyncTask("AsynacTask#6").execute("");
                //IntentService
                Intent service = new Intent(MainActivity.this,LocalIntentService.class);
                service.putExtra("task_action","com.hzk.action.hzk1");
                startService(service);
                service.putExtra("task_action","com.hzk.action.hzk2");
                startService(service);
                service.putExtra("task_action","com.hzk.action.hzk3");
                startService(service);
            }
        });
        btn_click02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Runnable command = new Runnable() {
                    @Override
                    public void run() {
                        SystemClock.sleep(2000);
                    }
                };
               //1.fixedThreadpool
                ExecutorService fixedThreadpool = Executors.newFixedThreadPool(4);
                fixedThreadpool.execute(command);
                //2.CachedThreadPool
                ExecutorService cachedThreadpool = Executors.newCachedThreadPool();
                cachedThreadpool.execute(command);

                ScheduledExecutorService scheduledpool = Executors.newScheduledThreadPool(4);
                //2000ms后执行command
                scheduledpool.schedule(command,2000, TimeUnit.MICROSECONDS);
                //延迟10s后，每隔100ms执行一次command
                scheduledpool.scheduleAtFixedRate(command,10,1000,TimeUnit.MICROSECONDS);

                ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();
                singleThreadPool.execute(command);
            }
        });
//        try {
//            URL url = new URL("http://www.sina.com.cn");
//            new DownloadFileTask().execute(url);
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }

    }
}
