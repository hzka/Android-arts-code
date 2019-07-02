package com.example.hzk.broadcastrecviver;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.net.URI;

public class TestCV extends AppCompatActivity {
     private Button btn_add,btn_update,btn_delete,btn_query;
    private String newId;
    private final static String TAG = "TestCV";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_cv);
        btn_add = (Button) findViewById(R.id.add_data);
        btn_delete = (Button) findViewById(R.id.delete_data);
        btn_update = (Button) findViewById(R.id.update_data);
        btn_query = (Button) findViewById(R.id.query_data);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //通过Content Provider来添加数据
                Uri uri = Uri.parse("content://com.example.hzk.broadcastrecviver.provider/book");
                ContentValues values = new ContentValues();
                values.put("name","A Clash of Kings");
                values.put("author","George Martin");
                values.put("pages",1040);
                values.put("price",22.85);
                Uri newUri = getContentResolver().insert(uri,values);
                newId = newUri.getPathSegments().get(1);
            }
        });
        btn_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //通过Content Provider来添加数据
                Uri uri = Uri.parse("content://com.example.hzk.broadcastrecviver.provider/book");
                Cursor cursor = getContentResolver().query(uri,null,null,null,null);
                if (cursor.moveToFirst()) {
                    do {
                        //遍历Cursor对象，取出并打印数据
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String author = cursor.getString(cursor.getColumnIndex("author"));
                        int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                        double price = cursor.getDouble(cursor.getColumnIndex("price"));
                        Log.d(TAG, "book name is:"+name);
                        Log.d(TAG, "book author is:"+author);
                        Log.d(TAG, "book pages is:"+pages);
                        Log.d(TAG, "book price is:" + price);
                    } while (cursor.moveToNext());
                }
                cursor.close();
            }
        });
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //更新数据
                Uri uri = Uri.parse("content://com.example.hzk.broadcastrecviver.provider/book"+newId);
                ContentValues values = new ContentValues();
                values.put("name","A storm of swords");
                values.put("pages",1216);
                values.put("price",24.05);
                getContentResolver().update(uri,values,null,null);
            }
        });
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("content://com.example.hzk.broadcastrecviver.provider/book"+newId);
                getContentResolver().delete(uri,null,null);
            }
        });
    }
}
