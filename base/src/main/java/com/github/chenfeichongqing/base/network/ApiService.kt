package com.github.chenfeichongqing.base.network


import com.github.chenfeichongqing.base.data.model.bean.ApiResponse
import com.github.chenfeichongqing.base.data.model.bean.UserInfo
import retrofit2.http.*

/**
 * 作者　: hegaojian
 * 时间　: 2019/12/23
 * 描述　: 网络API
 */
interface ApiService {

    companion object {
        const val SERVER_URL = "https://wanandroid.com/"
    }
    /**
     * 登录
     */
    @FormUrlEncoded
    @POST("user/login")
    suspend fun login(
        @Field("username") username: String,
        @Field("password") pwd: String
    ): ApiResponse<UserInfo>

    /**
     * 注册
     */
    @FormUrlEncoded
    @POST("user/register")
    suspend fun register(
        @Field("username") username: String, @Field("password") pwd: String, @Field(
            "repassword"
        ) rpwd: String
    ): ApiResponse<Any>

}