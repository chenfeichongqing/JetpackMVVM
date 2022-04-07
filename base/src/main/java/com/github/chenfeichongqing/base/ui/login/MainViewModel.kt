package com.github.chenfeichongqing.base.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.github.chenfeichongqing.base.data.model.bean.ApiPagerResponse
import com.github.chenfeichongqing.base.data.model.bean.BillInfo
import com.github.chenfeichongqing.base.network.apiService
import com.github.chenfeichongqing.base.ui.Repository
import com.github.chenfeichongqing.mvvmlib.base.viewmodel.BaseViewModel
import com.github.chenfeichongqing.mvvmlib.ext.request
import com.github.chenfeichongqing.mvvmlib.state.ResultState
import kotlinx.coroutines.flow.Flow


class MainViewModel : BaseViewModel() {

    //方式1  自动脱壳过滤处理请求结果，判断结果是否成功
    var billsResult = MutableLiveData<ResultState<ApiPagerResponse<List<BillInfo>>>>()

    fun getBillPagingData(): Flow<PagingData<BillInfo>> {
        return Repository.getBillPagingData().cachedIn(viewModelScope)
    }


    fun getBilllist(page: Int) {
        //1.这种是在 Activity/Fragment的监听回调中拿到已脱壳的数据（项目有基类的可以用）
        request(
            { apiService.getBillBypage(page,20) }//请求体
            , billsResult,//请求的返回结果，请求成功与否都会改变该值，在Activity或fragment中监听回调结果，具体可看loginActivity中的回调
            true,//是否显示等待框，，默认false不显示 可以默认不传
            "正在登录中..."//等待框内容，可以默认不填请求网络中...
        )
        //2.这种是在Activity/Fragment中的监听拿到未脱壳的数据，你可以自己根据code做业务需求操作（项目没有基类的可以用）
        /*requestNoCheck({HttpRequestCoroutine.login(username,password)},loginResult2,true)*/

        //3. 这种是直接在当前ViewModel中就拿到了脱壳数据数据，做一层封装再给Activity/Fragment，如果 （项目有基类的可以用）
        /*request({apiService.login(username, password)},{
            //请求成功 已自动处理了 请求结果是否正常
        },{
            //请求失败 网络异常，或者请求结果码错误都会回调在这里
        })*/

        //4.这种是直接在当前ViewModel中就拿到了未脱壳数据数据，（项目没有基类的可以用）
        /*requestNoCheck({HttpRequestCoroutine.login(username,password)},{
            //请求成功 自己拿到数据做业务需求操作
            if(it.errorCode==0){
                //结果正确
            }else{
                //结果错误
            }
        },{
            //请求失败 网络异常回调在这里
        })*/
    }
}