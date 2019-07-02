package com.nwu.hzk.myapplication;

import android.icu.text.SimpleDateFormat;
import android.os.AsyncTask;
import android.util.Log;

import java.util.Date;

/**
 * Created by hzk on 2019/6/27.
 */
//点击按钮同时启动五个AsyncTask任务，每一个会休眠3s来模拟耗时操作，同时把每个AsyncTask执行结束的时间打印。
    //串行执行的验证。
public class MyAsyncTask extends AsyncTask<String,Integer,String> {
    private String TAG = "MyAsyncTask";
    private String mName = "AsyncTask";
    public MyAsyncTask(String name){
        super();
        mName = name;
    }
    @Override
    protected String doInBackground(String... params) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mName;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Log.e(TAG, s+"execute finish at:"+df.format(new Date()) );
        }
    }
}
