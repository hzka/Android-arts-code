package com.example.hzk.broadcastrecviver;

import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ReadContacts extends AppCompatActivity {
    private static String TAG = "ReadContacts";
    ArrayAdapter<String> adapter;
    List<String> contactsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_contacts);
        //获取Bundle对象，并进行log输出，完成信息传递
        Bundle getbundle = getIntent().getExtras();
        Log.i(TAG, "onCreate: " + getbundle.getString("Data"));
        //获取ListView控件的实例
        ListView contactsview = (ListView) findViewById(R.id.contacts_view);
        //新建适配器
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, contactsList);
        //设置适配器
        contactsview.setAdapter(adapter);
        //READ_CONTACTS是危险权限，可能需要动态申请。记得增加权限。
        readContact();
    }

    private void readContact() {
        Cursor cursor = null;
        try {
            //查找联系人数据
            cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    //获取联系人姓名
                    String displayname = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    //获取联系人手机号
                    String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    contactsList.add(displayname + "\n" + number);
                }
                //刷新ListView
                adapter.notifyDataSetChanged();
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //将Cursor对象关闭。
            if(cursor!=null){
                cursor.close();
            }
        }
    }
}
