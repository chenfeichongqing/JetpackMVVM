package com.github.chenfeichongqing.mvvmlib.util;

import android.view.View;

/**
 * Created by  on 2017/12/1.
 * 可防止多次点击的监听器
 */

public abstract class OnSingleClickListener implements View.OnClickListener {

    private long lastClickStamp;
    private static final long MIN_INTERVAL = 2000;

    @Override
    public void onClick(View v) {
        long currentStamp = System.currentTimeMillis();
        if (currentStamp - lastClickStamp >= MIN_INTERVAL) {
            lastClickStamp = currentStamp;
            onSingleClick(v);
        } else {
            onDuplicateClick(v);
        }
    }

    /**
     * 单次点击
     *
     * @param view 被点击的View
     */
    protected abstract void onSingleClick(View view);

    /**
     * 重复点击
     *
     * @param view 被点击的View
     */
    private void onDuplicateClick(View view) {

    }

}
