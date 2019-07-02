package com.nwu.hzk.myapplication;

import android.app.IntentService;
import android.content.Intent;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by hzk on 2019/6/27.
 */

public class LocalIntentService extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    private static final String TAG = "LocalIntentService";

    public LocalIntentService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String action = intent.getStringExtra("task_action");
        Log.d(TAG, "receive task:" + action);
        SystemClock.sleep(3000);
        if("com.hzk.action.hzk1".equals(action)){
            Log.d(TAG, "handle task"+action);
        }
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "Service onDestroy: ");
        super.onDestroy();
    }
}
