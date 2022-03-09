package com.github.chenfeichongqing.base.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.CompoundButton
import androidx.lifecycle.Observer
import com.github.chenfeichongqing.base.appViewModel
import com.github.chenfeichongqing.base.databinding.ActivityLoginBinding
import com.github.chenfeichongqing.base.ext.showMessage
import com.github.chenfeichongqing.base.ui.MainActivity
import com.github.chenfeichongqing.base.util.CacheUtil
import com.github.chenfeichongqing.mvvmlib.base.activity.BaseVmDbActivity
import com.github.chenfeichongqing.mvvmlib.ext.parseState

 class LoginActivity : BaseVmDbActivity<LoginViewModel,ActivityLoginBinding>() {


    override fun initView(savedInstanceState: Bundle?) {
        mViewBind.viewmodel = mViewModel
        mViewBind.click = ProxyClick()
    }

    companion object {
        fun start(context: Context) {
            val starter = Intent(context, LoginActivity::class.java)
            context.startActivity(starter)
        }
    }
    override fun createObserver() {
        mViewModel.loginResult.observe(this,Observer { resultState ->
            parseState(resultState, {
                //登录成功 通知账户数据发生改变鸟
                CacheUtil.setUser(it)
                CacheUtil.setIsLogin(true)
                appViewModel.userInfo.value = it
                MainActivity.start(this@LoginActivity)
                finish()
            }, {
                //登录失败
                (it.errorMsg)
            })
        })
    }

    inner class ProxyClick {

        fun clear() {
            mViewModel.username.set("")
        }
        fun login() {
            when {
                mViewModel.username.get().isEmpty() -> showMessage("请填写账号")
                mViewModel.password.get().isEmpty() -> showMessage("请填写密码")
                else -> mViewModel.loginReq(
                    mViewModel.username.get(),
                    mViewModel.password.get()
                )
            }
        }
        var onCheckedChangeListener =
            CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
                mViewModel.isShowPwd.set(isChecked)
            }
    }
}