package com.example.hzk.p221;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.SystemClock;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.example.hzk.p221.*;
/*
 *  项目名：  RemoteViewsSample
 *  包名：    com.liuguilin.remotrview.provider
 *  文件名:   AppWidgetImpl
 *  创建者:   LiuGuiLin
 *  创建时间:  2017/1/14 0014 下午 4:35
 *  描述：    小组件
 */
public class MyProviderReceiver extends AppWidgetProvider {

    public static final String TAG = "MyProviderReceiver";
    public static final String CLICK_ACTION = "com.liuguilin.remotrview.provider.click_action";

    private AppWidgetManager appWidgetManage;
    private float degree;
    private Bitmap bitmap;

    public MyProviderReceiver() {
        super();
    }

    @Override
    public void onReceive(final Context context, final Intent intent) {
        super.onReceive(context, intent);
        String action = intent.getAction();
        Log.i(TAG, "action:" + action);
        if (CLICK_ACTION.equals(action)) {
            Toast.makeText(context, "click it", Toast.LENGTH_SHORT).show();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    bitmap = BitmapFactory.decodeResource(context.getResources(), R.);
                    appWidgetManage = AppWidgetManager.getInstance(context);
                    for (int i = 0; i < 37; i++) {
                        degree = (i * 10) % 360;
                        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.wideget);
                        remoteViews.setImageViewBitmap(R.id.iv1, rotateBitmap(bitmap));
                        remoteViews.setImageViewBitmap(R.id.iv2, rotateBitmap(bitmap));
                        Intent intentClick = new Intent(CLICK_ACTION);
                        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intentClick, 0);
                        remoteViews.setOnClickPendingIntent(R.id.iv1, pendingIntent);
                        appWidgetManage.updateAppWidget(new ComponentName(context, MyProviderReceiver.class), remoteViews);
                        SystemClock.sleep(30);
                    }
                }
            }).start();
        }
    }

    //每次更新都会调用
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        Log.i(TAG, "onUpdate");
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget);
        Intent intentClick = new Intent(CLICK_ACTION);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intentClick, 0);
        remoteViews.setOnClickPendingIntent(R.id.iv1, pendingIntent);
        appWidgetManage.updateAppWidget(new ComponentName(context, MyProviderReceiver.class), remoteViews);
    }

    //动画
    private Bitmap rotateBitmap(Bitmap bitmap) {
        Matrix matrix = new Matrix();
        matrix.reset();
        matrix.setRotate(degree);
        Bitmap temBitmap = Bitmap.createBitmap(this.bitmap, 0, 0, this.bitmap.getWidth(), this.bitmap.getHeight(), matrix, true);
        return temBitmap;
    }
}
