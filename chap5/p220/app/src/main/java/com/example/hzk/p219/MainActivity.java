package com.example.hzk.p219;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.*;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;

/**
 * 功能：RemoteViews在通知栏上的应用。
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_send_notice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_send_notice = (Button) findViewById(R.id.btn_send_notice);
        btn_send_notice.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_send_notice:
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);

                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

                RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.layout_notification);
                remoteViews.setTextViewText(R.id.msg, "chapter_5");
                remoteViews.setImageViewResource(R.id.icon, R.mipmap.ic_launcher);
                remoteViews.setOnClickPendingIntent(R.id.open_activity2, pi);

                Notification notification = new NotificationCompat.Builder(this)
                        .setContentTitle("This is content title")
                        .setContentText("This is content text")
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                        .setContentIntent(pi)
                        .setCustomContentView(remoteViews)
                        .setAutoCancel(true)
                        .build();

                notificationManager.notify(2, notification);
                break;
            default:
                break;
        }
    }
}
