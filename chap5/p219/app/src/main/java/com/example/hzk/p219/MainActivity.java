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
                //步骤四：点击通知没有效果，使用pendingIntent来实现（延迟执行的Intent）
                //可以getActivity、getService、getBroadcast，第一个参数context，第二个没用，第三个intent对象，第四个是PI的行为，一般为零。
                //最后记得setContentIntent(pi)
                Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                PendingIntent pi = PendingIntent.getActivity(this,0,intent,0);
                //步骤一：NotificationManager对通知进行管理，调用Context的getSystemService可获得，该API可用于确定获取系统的哪一个服务
                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);



                //步骤二：通过Builder构造器来创建Notification对象，可以连缀任意多的设置方法来创建对象,注意是support-v4库的内容，兼容性好
                //设置内容包括标题、正文、创建时间、通知小图标、大图标。
                Notification notification = new NotificationCompat.Builder(this)
                        .setContentTitle("This is content title")
                        .setContentText("This is content text")
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                        .setContentIntent(pi)
                        .setAutoCancel(true)
                        .build();
                //步骤三：调用NotificationManager的notify就可以让通知显示出来了，notify接收两个参数，一个是id，确保每个通知的id是不同的，
                //另一个是Notification对象
                notificationManager.notify(1,notification);
                //步骤五：点击之后取消通知，在新的Activity中写这个，cancel的1指的是刚才notify接收两个参数中，或者setAutoCancel
//                NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//                manager.cancel(1);
                break;
            default:
                break;
        }
    }
}
