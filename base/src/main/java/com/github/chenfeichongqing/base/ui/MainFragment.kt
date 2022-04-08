package com.github.chenfeichongqing.base.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.chenfeichongqing.base.R
import com.github.chenfeichongqing.base.adapter.BillPageAdapter
import com.github.chenfeichongqing.base.databinding.ActivityMainBinding
import com.github.chenfeichongqing.base.databinding.FragmentMainBinding
import com.github.chenfeichongqing.base.ui.common.BaseBillActivity
import com.github.chenfeichongqing.base.ui.common.BaseBillFragment
import com.github.chenfeichongqing.base.ui.login.MainViewModel
import com.github.chenfeichongqing.mvvmlib.util.PermissionHelper
import com.github.chenfeichongqing.mvvmlib.utilcode.constant.PermissionConstants
import com.github.chenfeichongqing.mvvmlib.utilcode.util.ColorUtils
import com.github.chenfeichongqing.mvvmlib.utilcode.util.PermissionUtils
import com.kingja.loadsir.core.LoadService
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import me.hgj.jetpackmvvm.demo.app.ext.init
import me.hgj.jetpackmvvm.demo.app.ext.loadServiceInit
import me.hgj.jetpackmvvm.demo.app.ext.showLoading


/**
 * ```
 * author: Blankj
 * blog  : http://blankj.com
 * time  : 2016/09/29
 * desc  : MainActivity
 * ```
 */
class MainFragment : BaseBillFragment<MainViewModel, FragmentMainBinding>() {

    private val billPageAdapter = BillPageAdapter()
    //界面状态管理者
    private lateinit var loadsir: LoadService<Any>


    override fun initView(savedInstanceState: Bundle?) {
        mDatabind.mainRv.layoutManager = LinearLayoutManager(activity)
        mDatabind.mainRv.adapter = billPageAdapter
        mDatabind.mainRv.setHasFixedSize(true)
        mDatabind.mainRv.itemAnimator = null
        //初始化 SwipeRefreshLayout
        mDatabind.swipeRefresh.init {
            //触发刷新监听时请求数据
            //请求数据
            lifecycleScope.launch {
                mViewModel.getBillPagingData().collect {
                    billPageAdapter.submitData(it)
                }
            }
        }

    }
    override fun createObserver() {
        //状态页配置 swipeRefresh参数表示你要包裹的布局
        loadsir = loadServiceInit(mDatabind.swipeRefresh) {
            //点击错误重试时触发的操作
            loadsir.showLoading()
            //请求数据
            lifecycleScope.launch {
                mViewModel.getBillPagingData().collect {
                    billPageAdapter.submitData(it)
                }
            }
        }

    }
    override fun lazyLoadData() {

    }
}
