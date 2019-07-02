package com.example.hzk.broadcastrecviver;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by hzk on 2019/6/13.
 */

public class MyDatabaseHelper extends SQLiteOpenHelper {
    public static final String CREATE_BOOK = "create table Book(" +
            "id integer primary key autoincrement," +
            "author test," +
            "pages integer," +
            "price real," +
            "name text)";
    public static final String CREATE_CATEGORY = "create table Category(" +
            "id integer primary key autoincrement," +
            "category_name test," +
            "category_code integer)";
    private Context mcontext;

    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mcontext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BOOK);//注意OnCreate只能执行一次。
        db.execSQL(CREATE_CATEGORY);
        Toast.makeText(mcontext, "CREATE succeed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Book");
        db.execSQL("drop table if exists Category");
        onCreate(db);

    }
}
