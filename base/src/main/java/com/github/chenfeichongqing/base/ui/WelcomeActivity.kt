package com.github.chenfeichongqing.base.ui

import android.os.Bundle
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.widget.ImageView
import androidx.lifecycle.lifecycleScope
import com.github.chenfeichongqing.base.R
import com.github.chenfeichongqing.base.ui.login.LoginActivity
import com.github.chenfeichongqing.base.util.CacheUtil
import com.github.chenfeichongqing.mvvmlib.base.activity.BaseActivity
import com.github.chenfeichongqing.mvvmlib.util.PermissionHelper
import com.github.chenfeichongqing.mvvmlib.utilcode.constant.PermissionConstants
import com.github.chenfeichongqing.mvvmlib.utilcode.util.PermissionUtils
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class WelcomeActivity : BaseActivity() {

    private val splashDuration = 3 * 1000L

    private val alphaAnimation by lazy {
        AlphaAnimation(0.5f, 1.0f).apply {
            duration = splashDuration
            fillAfter = true
        }
    }

    private val scaleAnimation by lazy {
        ScaleAnimation(1f, 1.05f, 1f, 1.05f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f).apply {
            duration = splashDuration
            fillAfter = true
        }
    }

    override fun setupViews() {
        super.setupViews()
        findViewById<ImageView>(R.id.ivSlogan).startAnimation(alphaAnimation)
        findViewById<ImageView>(R.id.ivSplashPicture).startAnimation(scaleAnimation)
        lifecycleScope.launch {
            delay(splashDuration)
            val user = CacheUtil.getUser();
            if (user==null){
                LoginActivity.start(this@WelcomeActivity)
            }
            else{
                MainActivity.start(this@WelcomeActivity)
            }
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWriteExternalStoragePermission()
    }
    private fun requestWriteExternalStoragePermission() {

        PermissionHelper.request(this, object : PermissionUtils.SimpleCallback {
            override fun onGranted() {
                setContentView(R.layout.activity_splash)
            }
            override fun onDenied() {
            }
        }, PermissionConstants.STORAGE,PermissionConstants.PHONE)
    }
}