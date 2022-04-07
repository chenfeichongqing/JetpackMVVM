package com.github.chenfeichongqing.base.network


import com.github.chenfeichongqing.base.data.model.bean.ApiPagerResponse
import com.github.chenfeichongqing.base.data.model.bean.ApiResponse
import com.github.chenfeichongqing.base.data.model.bean.BillInfo
import com.github.chenfeichongqing.base.data.model.bean.UserInfo
import retrofit2.http.*

/**
 * 作者　: hegaojian
 * 时间　: 2019/12/23
 * 描述　: 网络API
 */
interface ApiService {

    companion object {
        const val SERVER_URL = "http://1.14.108.171:8084/bill/"
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


    @FormUrlEncoded
    @POST("bill/list")
    suspend fun getBillBypage(
        @Field("page") page: Int,
        @Field("size") size: Int
    ): ApiResponse<ApiPagerResponse<List<BillInfo>>>

}