package com.example.hzk.myapplication02;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/***
 * 功能：Serializable和Parcelable。
 */
public class MainActivity extends AppCompatActivity {

    private Button btn_click;
    public static final String TAG  = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        UserManager.sUserid = 2;
//        Log.i(TAG,  "MainActivity"+UserManager.sUserid);
        btn_click = (Button) findViewById(R.id.btn_click);
        btn_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Person person = new Person();
                person.setName("kevinhe");
                person.setAge(20);
//                Intent intent02 = new Intent(MainActivity.this,Main2Activity.class);
//                intent02.putExtra("person_data",person);
//                startActivity(intent02);
                try {
                    File file = new File("D:\\cache.txt");
                    //序列化过程
                    ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
                    out.writeObject(person);
                    out.close();
                    //反序列化过程
                    ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
                    Person newperson = (Person) in.readObject();
                    Log.i(TAG, "Person name: "+newperson.getName());
                    Log.i(TAG, "Person age: "+newperson.getAge());
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
