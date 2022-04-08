package com.github.chenfeichongqing.base.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.navigation.Navigation
import com.github.chenfeichongqing.base.R
import com.github.chenfeichongqing.base.databinding.ActivityMainBinding
import com.github.chenfeichongqing.base.ui.common.BaseBillActivity
import com.github.chenfeichongqing.base.ui.login.MainViewModel
import com.github.chenfeichongqing.mvvmlib.base.network.manager.NetState
import com.github.chenfeichongqing.mvvmlib.util.PermissionHelper
import com.github.chenfeichongqing.mvvmlib.utilcode.constant.PermissionConstants
import com.github.chenfeichongqing.mvvmlib.utilcode.util.PermissionUtils
import com.github.chenfeichongqing.mvvmlib.utilcode.util.ToastUtils

/**
 * 项目主页Activity
 */
class MainActivity : BaseBillActivity<MainViewModel, ActivityMainBinding>() {

    var exitTime = 0L

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
    override fun setupViews() {
        //进入首页检查更新
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val nav = Navigation.findNavController(this@MainActivity, R.id.host_fragment)
                if (nav.currentDestination != null && nav.currentDestination!!.id != R.id.mainfragment) {
                    //如果当前界面不是主页，那么直接调用返回即可
                    nav.navigateUp()
                } else {
                    //是主页
                    if (System.currentTimeMillis() - exitTime > 2000) {
                        ToastUtils.showShort("再按一次退出程序")
                        exitTime = System.currentTimeMillis()
                    } else {
                        finish()
                    }
                }
            }
        })
    }

    /**
     * 示例，在Activity/Fragment中如果想监听网络变化，可重写onNetworkStateChanged该方法
     */
    override fun onNetworkStateChanged(netState: NetState) {
        super.onNetworkStateChanged(netState)
        if (netState.isSuccess) {
            Toast.makeText(applicationContext, "我特么终于有网了啊!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(applicationContext, "我特么怎么断网了!", Toast.LENGTH_SHORT).show()
        }
    }
    override fun isLight(): Boolean {
        return false
    }

    override fun statusColor(): Int {
        return R.color.maincolor
    }
}
