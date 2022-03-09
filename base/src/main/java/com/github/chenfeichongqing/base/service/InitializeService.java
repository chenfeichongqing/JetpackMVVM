package com.github.chenfeichongqing.base.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import com.github.chenfeichongqing.mvvmlib.utilcode.util.LogUtils;

/**
 * Created by mingjun on 16/8/25.
 */
public class InitializeService extends IntentService {

    private static final String ACTION_INIT_WHEN_APP_CREATE = "com.anly.githubapp.service.action.INIT";

    public InitializeService() {
        super("InitializeService");
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, InitializeService.class);
        intent.setAction(ACTION_INIT_WHEN_APP_CREATE);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_INIT_WHEN_APP_CREATE.equals(action)) {
                performInit();
            }
        }
    }

    private void performInit() {
        LogUtils.vTag("InitializeService","performInit begin:" + System.currentTimeMillis());
        //初始化第三方库

        LogUtils.vTag("InitializeService","performInit end:" + System.currentTimeMillis());
    }
}
