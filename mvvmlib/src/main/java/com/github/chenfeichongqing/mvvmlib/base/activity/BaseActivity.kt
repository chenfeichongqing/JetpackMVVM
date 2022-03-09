/*
 * Copyright (c) 2020. vipyinzhiwei <vipyinzhiwei@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.chenfeichongqing.mvvmlib.base.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.appcompat.app.AppCompatActivity
import com.github.chenfeichongqing.mvvmlib.R
import com.github.chenfeichongqing.mvvmlib.util.ActivityCollector
import com.github.chenfeichongqing.mvvmlib.util.ShareUtil
import com.gyf.barlibrary.ImmersionBar
import java.lang.ref.WeakReference

/**
 * 应用程序中所有Activity的基类。
 *
 * @author vipyinzhiwei
 * @since  2020/4/29
 */
@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity() {
    /**
     * 判断当前Activity是否在前台。
     */
    protected var isActive: Boolean = false

    /**
     * 当前Activity的实例。
     */
    protected var activity: Activity? = null

    /** 当前Activity的弱引用，防止内存泄露  */
    private var activityWR: WeakReference<Activity>? = null

    /**
     * 日志输出标志
     */
    protected val TAG: String = this.javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity = this
        activityWR = WeakReference(activity!!)
        ActivityCollector.pushTask(activityWR)

    }
    override fun onResume() {
        super.onResume()
        isActive = true
    }

    override fun onPause() {
        super.onPause()
        isActive = false
    }

    override fun onDestroy() {
        super.onDestroy()
        activity = null
        ActivityCollector.removeTask(activityWR)
    }

    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
       // setStatusBarBackground(R.color.colorPrimaryDark)
        setupViews()
    }

    override fun setContentView(layoutView: View) {
        super.setContentView(layoutView)
       // setStatusBarBackground(R.color.colorPrimaryDark)
        setupViews()
    }

    protected open fun setupViews() {
        val navigateBefore = findViewById<ImageView>(R.id.ivNavigateBefore)
        val tvTitle = findViewById<TextView>(R.id.tvTitle)
        navigateBefore?.setOnClickListener { finish() }
        tvTitle?.isSelected = true  //获取焦点，实现跑马灯效果。
    }

    /**
     * 设置状态栏背景色
     */
    open fun setStatusBarBackground(@ColorRes statusBarColor: Int) {
        ImmersionBar.with(this).statusBarDarkFont(true, 0.2f).statusBarColor(statusBarColor).fitsSystemWindows(true).init()
    }

    /**
     * 调用系统原生分享
     *
     * @param shareContent 分享内容
     * @param shareType SHARE_MORE=0，SHARE_QQ=1，SHARE_WECHAT=2，SHARE_WEIBO=3，SHARE_QQZONE=4
     */
    protected fun share(shareContent: String, shareType: Int) {
        ShareUtil.share(this, shareContent, shareType)
    }
}
