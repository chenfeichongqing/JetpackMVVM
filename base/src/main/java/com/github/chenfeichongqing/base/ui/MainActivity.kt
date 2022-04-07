package com.github.chenfeichongqing.base.ui

import android.content.Context
import android.content.Intent
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.chenfeichongqing.base.R
import com.github.chenfeichongqing.base.adapter.BillPageAdapter
import com.github.chenfeichongqing.base.databinding.ActivityMainBinding
import com.github.chenfeichongqing.base.ui.common.BaseBillActivity
import com.github.chenfeichongqing.base.ui.common.FooterAdapter
import com.github.chenfeichongqing.base.ui.login.MainViewModel
import com.github.chenfeichongqing.mvvmlib.util.PermissionHelper
import com.github.chenfeichongqing.mvvmlib.utilcode.constant.PermissionConstants
import com.github.chenfeichongqing.mvvmlib.utilcode.util.ColorUtils
import com.github.chenfeichongqing.mvvmlib.utilcode.util.PermissionUtils
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


/**
 * ```
 * author: Blankj
 * blog  : http://blankj.com
 * time  : 2016/09/29
 * desc  : MainActivity
 * ```
 */
class MainActivity : BaseBillActivity<MainViewModel,ActivityMainBinding>() {

    private val billPageAdapter = BillPageAdapter()

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
        super.setupViews()
        mViewBind.mainRv.layoutManager = LinearLayoutManager(this)
        mViewBind.mainRv.adapter = billPageAdapter.withLoadStateFooter(FooterAdapter { billPageAdapter.retry() })
        mViewBind.mainRv.setHasFixedSize(true)
        mViewBind.mainRv.itemAnimator = null

    }
    override fun createObserver() {
        lifecycleScope.launch {
            mViewModel.getBillPagingData().collect {
                billPageAdapter.submitData(it)
            }
        }
    }

    override fun isLight(): Boolean {
        return false
    }

    override fun statusColor(): Int {
        return  ColorUtils.getColor(R.color.maincolor)
    }
}
