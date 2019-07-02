package com.example.hzk.fc350;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private MyService.DowloadBinder dowloadBinder;
    private Button btn_start, btn_stop,btn_bind_service,btn_unbind_service;
   private ServiceConnection connection = new ServiceConnection() {
       @Override
       public void onServiceConnected(ComponentName name, IBinder service) {
           //向下转型得到DownloadBinder实例，有了这个实例，活动和服务之间建立了密切的关系。
           dowloadBinder = (MyService.DowloadBinder) service;
           //可根据不同场景具体调用dowloadBinder中的不同方法，指挥服务去干什么
           dowloadBinder.startDownload();
           dowloadBinder.getProgress();
       }

       @Override
       public void onServiceDisconnected(ComponentName name) {
       }
   };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_start = (Button) findViewById(R.id.btn_start);
        btn_stop = (Button) findViewById(R.id.btn_stop);
        btn_bind_service = (Button) findViewById(R.id.btn_bind_service);
        btn_unbind_service = (Button) findViewById(R.id.btn_unbind_service);
        btn_start.setOnClickListener(this);
        btn_stop.setOnClickListener(this);
        btn_bind_service.setOnClickListener(this);
        btn_unbind_service.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_start:
                //构建Intent对象
                Intent startIntent = new Intent(this,MyService.class);
                //调用startService来启动MyService这个任务。定义于Context类中的，所以可以直接调用startService方法。
                startService(startIntent);//启动服务
                break;
            case R.id.btn_stop:
                Intent stopIntent = new Intent(this,MyService.class);
                //除了这种调用方式外，还有那些停止方法？MyService内部的任何一处位置调用StopSelf即可让该服务停止。
                stopService(stopIntent);//停止服务
                break;
            case R.id.btn_bind_service:
                //构建Intent对象，调用bindService实现Activity和Service的绑定
                Intent binIntent = new Intent(this,MyService.class);
                //第一个参数是刚构建出来的Intent对象，第二个参数是前面创建的ServiceConnection实例；
                //第三个参数BIND_AUTO_CREATE是活动和服务绑定后自动创建服务。会使得onCreate方法得以执行但onStartCommand不会执行。
                bindService(binIntent,connection,BIND_AUTO_CREATE);//绑定服务
                break;
            case R.id.btn_unbind_service:
                unbindService(connection);
                break;
            default:
                break;
        }
    }
}
