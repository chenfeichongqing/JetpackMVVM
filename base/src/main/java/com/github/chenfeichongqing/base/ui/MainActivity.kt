package com.github.chenfeichongqing.base.ui

import android.content.Context
import android.content.Intent
import com.github.chenfeichongqing.base.R
import com.github.chenfeichongqing.mvvmlib.base.activity.BaseVmActivity
import com.github.chenfeichongqing.mvvmlib.base.viewmodel.BaseViewModel
import com.github.chenfeichongqing.mvvmlib.util.PermissionHelper
import com.github.chenfeichongqing.mvvmlib.utilcode.constant.PermissionConstants
import com.github.chenfeichongqing.mvvmlib.utilcode.util.ColorUtils
import com.github.chenfeichongqing.mvvmlib.utilcode.util.PermissionUtils


/**
 * ```
 * author: Blankj
 * blog  : http://blankj.com
 * time  : 2016/09/29
 * desc  : MainActivity
 * ```
 */
class MainActivity : BaseVmActivity<BaseViewModel>() {


    companion object {
        fun start(context: Context) {
            PermissionHelper.request(context, object : PermissionUtils.SimpleCallback {
                override fun onGranted() {
                    val starter = Intent(context, MainActivity::class.java)
                    context.startActivity(starter)
                }
                override fun onDenied() {
                }
            }, PermissionConstants.LOCATION)
        }
    }

    override fun layoutId(): Int {
      return R.layout.activity_main
    }

    override fun isLight(): Boolean {
        return true
    }

    override fun statusColor(): Int {
        return ColorUtils.getColor(R.color.colorPrimary)
    }

    override fun createObserver() {

    }


}
