package com.github.chenfeichongqing.mvvmlib.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.github.chenfeichongqing.mvvmlib.base.viewmodel.BaseViewModel
import com.github.chenfeichongqing.mvvmlib.ext.inflateBindingWithGeneric

/**
 * 作者　: hegaojian
 * 时间　: 2019/12/12
 * 描述　: ViewModelFragment基类，自动把ViewModel注入Fragment和Databind注入进来了
 * 需要使用Databind的清继承它
 */
abstract class BaseVmDbFragment<VM : BaseViewModel, DB : ViewDataBinding> : BaseVmFragment<VM>() {

    //该类绑定的ViewDataBinding
    lateinit var mDatabind: DB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mDatabind = inflateBindingWithGeneric(layoutInflater,container,false)
        mDatabind.lifecycleOwner = this
        return mDatabind.root
    }

}