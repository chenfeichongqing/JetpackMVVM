package com.github.chenfeichongqing.base.ui.common

import androidx.databinding.ViewDataBinding
import com.github.chenfeichongqing.base.ext.dismissLoadingExt
import com.github.chenfeichongqing.base.ext.showLoadingExt
import com.github.chenfeichongqing.mvvmlib.base.fragment.BaseVmDbFragment
import com.github.chenfeichongqing.mvvmlib.base.viewmodel.BaseViewModel


abstract class BaseBillFragment<VM : BaseViewModel, DB : ViewDataBinding> : BaseVmDbFragment<VM, DB>() {

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