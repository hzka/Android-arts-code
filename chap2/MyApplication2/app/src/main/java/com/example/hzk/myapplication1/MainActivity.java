package com.example.hzk.myapplication1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

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
                Intent intent02 = new Intent(MainActivity.this,Main2Activity.class);
                intent02.putExtra("person_data",person);
                startActivity(intent02);
            }
        });
    }
}
