package com.github.chenfeichongqing.base.data.model.bean
import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * 作者　: hegaojian
 * 时间　: 2019/12/23
 * 描述　: 账户信息
 */
@SuppressLint("ParcelCreator")
@Parcelize

data class UserInfo(var userid: Int = 0,
                    var username: String="",
                    var nickname: String="",
                    var password: String="",
                    var creattime: String="") : Parcelable
