package com.example.hzk.fc360;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

//继承自Service，是一个服务。每一个服务都是注册在XML中的。
public class MyService extends Service {
    private DowloadBinder mBinder = new DowloadBinder();
    private final static String TAG = "MyService" ;
    public MyService() {
    }
    //服务创建的时候调用
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "" +
                "" +
                "onCreate exec: ");
        Intent intent = new Intent(this,MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this,0,intent,0);
        //构建Notification对象及相应的PendingIntent方法。
        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle("This is content title")
                .setContentText("This is content text")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher_round))
                .setContentIntent(pi)
                .build();
        //并没有使用建NotificationManager让他显示出来，而是使用startForeground让MyService变成一个前台服务。
        startForeground(1,notification);
    }

    //每次服务启动的时候调用，希望服务一旦启动就立刻执行某个动作，则将其写在onStartCommand中。
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand exec: ");
        new Thread(new Runnable() {
            @Override
            public void run() {
                //处理具体逻辑
                stopSelf();
            }
        }).start();
        return super.onStartCommand(intent, flags, startId);
    }

    //服务销毁的时候调用，回收不再使用的资源。
    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy exec: ");
        super.onDestroy();
    }

    //唯一的抽一的抽象方法，需要在子类中实现，暂时忽略。
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return mBinder;
    }

    class DowloadBinder extends Binder{
        public void startDownload(){
            Log.d(TAG, "startDownload exec: ");
        }
        public int getProgress(){
            Log.d(TAG, "getProgress exec: ");
            return 0;
        }
    }
}
