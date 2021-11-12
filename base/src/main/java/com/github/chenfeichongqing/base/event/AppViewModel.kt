package com.github.chenfeichongqing.base.event

import com.github.chenfeichongqing.base.data.model.bean.UserInfo
import com.github.chenfeichongqing.base.util.CacheUtil
import com.github.chenfeichongqing.mvvmlib.base.viewmodel.BaseViewModel
import com.kunminx.architecture.ui.callback.UnPeekLiveData

/**
 * 作者　: hegaojian
 * 时间　: 2019/12/23
 * 描述　:APP全局的ViewModel，可以存放公共数据，当他数据改变时，所有监听他的地方都会收到回调,也可以做发送消息
 * 比如 全局可使用的 地理位置信息，账户信息,App的基本配置等等，
 */
class AppViewModel : BaseViewModel() {

    //App的账户信息
    var userInfo = UnPeekLiveData.Builder<UserInfo>().setAllowNullValue(true).create()


    init {
        //默认值保存的账户信息，没有登陆过则为null
        userInfo.value = CacheUtil.getUser()
    }
}