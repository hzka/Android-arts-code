package com.example.hzk.myapplication;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button btn_click,btn_click02;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_click = (Button) findViewById(R.id.btn_click);
        btn_click02 = (Button) findViewById(R.id.btn_click_02);
        //AlertDialog:当前界面弹出一个对话框。置顶于所有元素之上，屏蔽掉其他控件。
        btn_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("This is Dialog")
                        .setMessage("Something important")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.show();
                Toast.makeText(MainActivity.this,"hello",Toast.LENGTH_LONG).show();
            }
        });
        //ProgressDialog:类似，会在对话框显示一个进度条，一般用于表示当前操作比较耗时，让用户耐心等待。
        btn_click02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setTitle("This is progressDialog");
                progressDialog.setMessage("loading...");
                progressDialog.setCancelable(true);
                progressDialog.show();
            }
        });
    }

}
