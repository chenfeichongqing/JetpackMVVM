package com.github.chenfeichongqing.base.widget.loadCallBack

import android.content.Context
import android.view.View
import com.github.chenfeichongqing.base.R
import com.kingja.loadsir.callback.Callback

class LoadingCallback : Callback() {

    override fun onCreateView(): Int {
        return R.layout.layout_loading
    }

    override fun onReloadEvent(context: Context?, view: View?): Boolean {
        return true
    }
}