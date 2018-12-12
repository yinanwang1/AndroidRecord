package com.example.arthurwang.helloworld.nov.utils;

import android.app.IntentService;
import android.content.Intent;
import android.os.SystemClock;
import android.support.annotation.Nullable;

import com.socks.library.KLog;

/**
 * Created by arthurwang on 2018/12/11
 */
public class LocalIntentService extends IntentService {

    private static final String TAG = "LocalIntentService";

    public LocalIntentService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String action = intent.getStringExtra("task_action");
        KLog.d("receive task: " + action);

        SystemClock.sleep(3000);

        if ("com.ryg.action.TASK1".equals(action)) {
            KLog.d("handle task: " + action);
        }
    }

    @Override
    public void onDestroy() {
        KLog.d("service destoryed.");

        super.onDestroy();
    }
}
