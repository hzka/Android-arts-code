package com.example.hzk.fc350;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.IntDef;
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
        Log.d(TAG, "onCreate exec: ");
    }

    //每次服务启动的时候调用，希望服务一旦启动就立刻执行某个动作，则将其写在onStartCommand中。
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand exec: ");
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
