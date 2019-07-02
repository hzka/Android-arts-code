package com.example.hzk.broadcastrecviver;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class DatabaseProvider extends ContentProvider {
    public static final int BOOK_DIR = 0;//访问Book表中所有数据
    public static final int BOOK_ITEM = 1;//访问Book表中单条数据
    public static final int CATEGORY_DIR = 2;
    public static final int CATEGORY_ITEM = 3;

    public static final String AUTHORITY = "com.example.hzk.broadcastrecviver.provider";

    private static UriMatcher uriMatcher;
    private MyDatabaseHelper dbHelper;
    //静态代码块中对UriMatcher进行初始化操作，将期望的几中URI格式添加进去。
    static {
        //借助UriMatcher可以轻松实现匹配内容URI的功能，其提供了addURI方法，当使用match方法时，就可以将一个URI对象传入，
        // 返回值是某个能匹配这个Uri对象所对应的自定义代码。
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, "book", BOOK_DIR);
        uriMatcher.addURI(AUTHORITY, "book/#", BOOK_ITEM);
        uriMatcher.addURI(AUTHORITY, "category", CATEGORY_DIR);
        uriMatcher.addURI(AUTHORITY, "category/#", CATEGORY_ITEM);
    }

    public DatabaseProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        //更新数据
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int deleteRoows = 0;
        switch (uriMatcher.match(uri)) {
            case BOOK_DIR:
                deleteRoows = db.delete("Book", selection, selectionArgs);
                break;
            case BOOK_ITEM:
                String bookId = uri.getPathSegments().get(1);
                deleteRoows = db.delete("Book", "id=?", new String[]{bookId});
                break;
            case CATEGORY_DIR:
                deleteRoows = db.delete("Category", selection, selectionArgs);
                break;
            case CATEGORY_ITEM:
                String categoryId = uri.getPathSegments().get(1);
                deleteRoows = db.delete("Category", "id=?", new String[]{categoryId});
                break;
            default:
                break;
        }
        return deleteRoows;
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
        //添加数据
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Uri urireturn = null;
        switch (uriMatcher.match(uri)) {
            case BOOK_DIR:
            case BOOK_ITEM:
                long newBookId = db.insert("Book", null, values);
                urireturn = Uri.parse("content://" + AUTHORITY + "/book/" + newBookId);
                break;
            case CATEGORY_DIR:
            case CATEGORY_ITEM:
                long newCategoryId = db.insert("Category", null, values);
                urireturn = Uri.parse("content://" + AUTHORITY + "/book/" + newCategoryId);
                break;
        }
        return urireturn;
    }

    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.
        //创建MyDatabaseHelper的实例
        dbHelper = new MyDatabaseHelper(getContext(), "BookStore.db", null, 3);
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        // TODO: Implement this to handle query requests from clients.
        //查询数据，（1）先获取SQLiteDatabase实例，（2）根据URI参数判断用户想要访问那张表；（3）query查询返回cursor对象。
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = null;
        switch (uriMatcher.match(uri)) {
            case BOOK_DIR:
                cursor = db.query("Book", projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case BOOK_ITEM:
                //getPathSegments分割/符号，降分个结果放入到一个字符串列表中，那么列表第零个位置存储路径，第一个位置存储id。
                String bookId = uri.getPathSegments().get(1);
                cursor = db.query("Book", projection, "id=?", new String[]{bookId}, null, null, sortOrder);
                break;
            case CATEGORY_DIR:
                cursor = db.query("Category", projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case CATEGORY_ITEM:
                String CategoryId = uri.getPathSegments().get(1);
                cursor = db.query("Category", projection, "id=?", new String[]{CategoryId}, null, null, sortOrder);
                break;
            default:
                break;
        }
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        //更新数据
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int updaterows = 0;
        switch (uriMatcher.match(uri)) {
            case BOOK_DIR:
                updaterows = db.update("Book", values, selection, selectionArgs);
                break;
            case BOOK_ITEM:
                String bookId = uri.getPathSegments().get(1);
                updaterows = db.update("Book", values, "id=?", new String[]{bookId});
                break;
            case CATEGORY_DIR:
                updaterows = db.update("Category", values, selection, selectionArgs);
                break;
            case CATEGORY_ITEM:
                String categoryId = uri.getPathSegments().get(1);
                updaterows = db.update("Category", values, "id=?", new String[]{categoryId});
                break;
            default:
                break;
        }
        return updaterows;
    }
}
