package com.example.hzk.broadcastrecviver;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * 1.SQLite实现CRUD操作；
 * 2.使用Bundle传递数据。
 * 2.读取系统联系人，如何在自己的程序中访问其他应用程序的数据。
 * 3.ContentProvider实现跨程序数据共享
 */
public class MainActivity extends AppCompatActivity {
    private MyDatabaseHelper dbHelpser;
    private Button btn_createDB, btn_add_data, btn_update_data, btn_delete_data, btn_query_data,btn_read_contacts,btn_customize_cv;
    private final static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelpser = new MyDatabaseHelper(this, "BookStore.db", null, 3);
        btn_createDB = (Button) findViewById(R.id.btn_caeate_db);
        btn_add_data = (Button) findViewById(R.id.btn_add_data);
        btn_update_data = (Button) findViewById(R.id.btn_update_data);
        btn_delete_data = (Button) findViewById(R.id.btn_delete_data);
        btn_query_data = (Button) findViewById(R.id.btn_query_data);
        btn_read_contacts = (Button) findViewById(R.id.btn_read_contacts);
        btn_customize_cv = (Button) findViewById(R.id.btn_test_custormizeCV);
        btn_createDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelpser.getWritableDatabase();
            }
        });
        btn_add_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelpser.getWritableDatabase();//获取数据库进行操作
                ContentValues values = new ContentValues();
                //开始组装第一条数据
                values.put("name", "The Da Vinci Code");
                values.put("author", "Dan Brown");
                values.put("pages", 454);
                values.put("price", 16.96);
                db.insert("Book", null, values);//插入第一条数据
                values.clear();//清空数据
                //开始组装第二条数据
                values.put("name", "The Lost Symbol");
                values.put("author", "Dan Brown");
                values.put("pages", 510);
                values.put("price", 19.95);
                db.insert("Book", null, values);//插入第一条数据
//                values.clear();
            }
        });
        btn_update_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelpser.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("price", 10.99);
                db.update("Book", values, "name = ?", new String[]{"The Da Vinci Code" });
            }
        });

        btn_delete_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelpser.getWritableDatabase();
                db.delete("Book", "pages>?", new String[]{"500"});
            }
        });

        btn_query_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelpser.getWritableDatabase();
                //查询表中所有的数据
                Cursor cursor = db.query("Book", null, null, null, null, null, null);
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
            }
        });
        btn_read_contacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_01 = new Intent(MainActivity.this,ReadContacts.class);
                Bundle bundler = new Bundle();
                bundler.putString("Data","Android art is rubbish");
                intent_01.putExtras(bundler);
                startActivity(intent_01);
            }
        });
        btn_customize_cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_02 = new Intent(MainActivity.this,TestCV.class);
                startActivity(intent_02);
            }
        });
    }
}
