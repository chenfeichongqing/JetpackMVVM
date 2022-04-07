package com.github.chenfeichongqing.base.ui.common

import androidx.databinding.ViewDataBinding
import com.github.chenfeichongqing.base.ext.dismissLoadingExt
import com.github.chenfeichongqing.base.ext.showLoadingExt
import com.github.chenfeichongqing.mvvmlib.base.activity.BaseVmDbActivity
import com.github.chenfeichongqing.mvvmlib.base.viewmodel.BaseViewModel


abstract class BaseBillActivity<VM : BaseViewModel, DB : ViewDataBinding> : BaseVmDbActivity<VM, DB>() {

    /**
     * 打开等待框
     */
    override fun showLoading(message: String) {
        showLoadingExt(message)
    }

    /**
     * 关闭等待框
     */
    override fun dismissLoading() {
        dismissLoadingExt()
    }
}