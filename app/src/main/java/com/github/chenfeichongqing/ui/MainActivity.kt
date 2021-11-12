package com.github.chenfeichongqing.ui

import android.os.Bundle
import com.github.chenfeichongqing.R
import com.github.chenfeichongqing.mvvmlib.base.activity.BaseVmDbActivity
import com.github.chenfeichongqing.mvvmlib.base.viewmodel.BaseViewModel
import com.github.chenfeichongqing.databinding.ActivityMainBinding

class MainActivity : BaseVmDbActivity<BaseViewModel, ActivityMainBinding>() {

    override fun layoutId(): Int {
       return R.layout.activity_main
    }
    override fun initView(savedInstanceState: Bundle?) {

    }

    override fun showLoading(message: String) {

    }

    override fun dismissLoading() {

    }

    override fun createObserver() {

    }
}