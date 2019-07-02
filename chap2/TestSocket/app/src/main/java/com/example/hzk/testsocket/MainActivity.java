package com.example.hzk.testsocket;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * 功能：Socket模拟服务端和客户端进行通信
 */
public class MainActivity extends AppCompatActivity {
private Button btn_main;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_main = (Button) findViewById(R.id.btn_main);
        btn_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_01 = new Intent(MainActivity.this,TCPClientActivity.class);
                startActivity(intent_01);
            }
        });
    }
}
