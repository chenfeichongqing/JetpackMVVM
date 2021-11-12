package com.github.chenfeichongqing.mvvmlib.base.network.manager

import com.github.chenfeichongqing.mvvmlib.callback.livedata.event.EventLiveData


/**
 * 作者　: hegaojian
 * 时间　: 2020/5/2
 * 描述　: 网络变化管理者
 */
class NetworkStateManager private constructor() {

    val mNetworkStateCallback = EventLiveData<NetState>()

    companion object {
        val instance: NetworkStateManager by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            NetworkStateManager()
        }
    }

}