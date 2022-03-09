package com.github.chenfeichongqing.base.ui.login

import android.view.View
import androidx.databinding.ObservableInt
import androidx.lifecycle.MutableLiveData
import com.github.chenfeichongqing.base.data.model.bean.UserInfo
import com.github.chenfeichongqing.base.network.apiService
import com.github.chenfeichongqing.mvvmlib.base.viewmodel.BaseViewModel
import com.github.chenfeichongqing.mvvmlib.callback.databind.BooleanObservableField
import com.github.chenfeichongqing.mvvmlib.callback.databind.StringObservableField
import com.github.chenfeichongqing.mvvmlib.ext.request
import com.github.chenfeichongqing.mvvmlib.state.ResultState


class LoginViewModel : BaseViewModel() {
    //用户名
    var username = StringObservableField()

    //密码(登录注册界面)
    var password = StringObservableField()

    //是否显示明文密码（登录注册界面）
    var isShowPwd = BooleanObservableField()


    //用户名清除按钮是否显示   不要在 xml 中写逻辑 所以逻辑判断放在这里
    var clearVisible = object : ObservableInt(username){
        override fun get(): Int {
            return if(username.get().isEmpty()){
                View.GONE
            }else{
                View.VISIBLE
            }
        }
    }

    //密码显示按钮是否显示   不要在 xml 中写逻辑 所以逻辑判断放在这里
    var passwordVisible = object :ObservableInt(password){
        override fun get(): Int {
            return if(password.get().isEmpty()){
                View.GONE
            }else{
                View.VISIBLE
            }
        }
    }
    //方式1  自动脱壳过滤处理请求结果，判断结果是否成功
    var loginResult = MutableLiveData<ResultState<UserInfo>>()

    //方式2  不用框架帮脱壳，判断结果是否成功
//    var loginResult2 = MutableLiveData<ResultState<ApiResponse<UserInfo>>>()

    fun loginReq(username: String, password: String) {
        //1.这种是在 Activity/Fragment的监听回调中拿到已脱壳的数据（项目有基类的可以用）
        request(
            { apiService.login(username, password) }//请求体
            , loginResult,//请求的返回结果，请求成功与否都会改变该值，在Activity或fragment中监听回调结果，具体可看loginActivity中的回调
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