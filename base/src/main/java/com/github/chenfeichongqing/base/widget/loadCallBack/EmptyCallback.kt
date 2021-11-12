package com.github.chenfeichongqing.base.widget.loadCallBack

import com.github.chenfeichongqing.base.R
import com.kingja.loadsir.callback.Callback

class EmptyCallback : Callback() {

    override fun onCreateView(): Int {
        return R.layout.layout_empty
    }

}