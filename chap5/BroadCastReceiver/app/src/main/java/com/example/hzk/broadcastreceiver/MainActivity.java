package com.example.hzk.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * 1.接收系统广播：动态注册监听网络变化()；静态注册实现开机启动；
 * 2.发送自定义广播：发送标准广播，发送有序广播；
 * 3.使用本地广播:
 */
public class MainActivity extends AppCompatActivity {

    private Button btn_click, btn_nativeclick;
    private IntentFilter intentFilter;
    private NetworkChangedReceiver networkChangedReceiver;
    private LocalReceiver localReceiver;
    private LocalBroadcastManager localBroadcastManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        networkChangedReceiver = new NetworkChangedReceiver();
        registerReceiver(networkChangedReceiver, intentFilter);
        btn_click = (Button) findViewById(R.id.btn_testBroad);
        btn_nativeclick = (Button) findViewById(R.id.btn_testnativeBroad);
        btn_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.hzk.broadcastreceiver.MyBroadCastReceiver");
//                sendBroadcast(intent);
                sendOrderedBroadcast(intent, null);
            }
        });
        localBroadcastManager = LocalBroadcastManager.getInstance(this);//获取实例
        btn_nativeclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.hzk.LOCAL_BROADCAST");
                localBroadcastManager.sendBroadcast(intent);//发送本地广播
            }
        });
        intentFilter.addAction("com.example.hzk.LOCAL_BROADCAST");
        localReceiver = new LocalReceiver();
        localBroadcastManager.registerReceiver(localReceiver,intentFilter);
    }




    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(networkChangedReceiver);
        localBroadcastManager.unregisterReceiver(localReceiver);
    }

    class NetworkChangedReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            //getSystemService获取ConnectivityManager的实例，这是系统服务类，专门用来网络管理
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            //getActiveNetworkInfo得到NetworkInfo的实例；
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            //isAvailable判断网络是否可用
            if (networkInfo != null && networkInfo.isAvailable()) {
                Toast.makeText(context, "有网", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "没网", Toast.LENGTH_SHORT).show();
            }
        }
    }

    class LocalReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "received local receiver", Toast.LENGTH_LONG).show();
        }
    }
}
