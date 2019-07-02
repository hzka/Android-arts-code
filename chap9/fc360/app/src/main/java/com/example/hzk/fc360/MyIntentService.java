package com.example.hzk.fc360;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by hzk on 2019/6/23.
 */

public class MyIntentService extends IntentService {
    private static final String TAG = "MyIntentService";
    public MyIntentService(){
        super("MyIntentService");//调用有参构造函数
    }
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the wqwidong1orker thread, important only for debugging.
     */
    public MyIntentService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.i(TAG, "onHandleIntent MyIntentService: Thread is:"+Thread.currentThread().getId());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy MyIntentService: ");
    }
}
