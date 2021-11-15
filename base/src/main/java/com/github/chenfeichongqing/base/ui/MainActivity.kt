package com.github.chenfeichongqing.base.ui

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.github.chenfeichongqing.base.R
import com.github.chenfeichongqing.mvvmlib.util.PermissionHelper
import com.github.chenfeichongqing.mvvmlib.utilcode.constant.PermissionConstants
import com.github.chenfeichongqing.mvvmlib.utilcode.util.PermissionUtils


/**
 * ```
 * author: Blankj
 * blog  : http://blankj.com
 * time  : 2016/09/29
 * desc  : MainActivity
 * ```
 */
class MainActivity : AppCompatActivity() {


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
    override fun onCreate(savedInstanceState: Bundle?) {
        window.setBackgroundDrawable(null)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

}
