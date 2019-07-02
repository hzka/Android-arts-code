package com.example.hzk.application3;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/***
 * 功能：启动模式&&IntentFilter的匹配规则。换句话说，隐式调用Activity。
 */
public class MainActivity extends AppCompatActivity {

    private Button btn_click;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_click = (Button) findViewById(R.id.btn_click);
        btn_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.hzk.category.a");
                intent.addCategory("com.hzk.category.c");
//                intent.setDataAndType(Uri.parse("file:/ic_launcher.png"),"image/*");
//                intent.putExtra("time",System.currentTimeMillis());
//                intent.setDataAndType(Uri.parse("file://abc"),"image/*");
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }
}
