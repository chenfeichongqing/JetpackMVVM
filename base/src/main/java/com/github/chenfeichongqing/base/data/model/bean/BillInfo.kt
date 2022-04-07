package com.github.chenfeichongqing.base.data.model.bean

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@SuppressLint("ParcelCreator")
@Parcelize
data class BillInfo(
    var catogoryid: String="",
    var id: Int=0,
    var money: Int=0,
    var time: String="",
    var title: String="",
    var type: String="",
    var remark:String ="",
    var userid: Int=0
): Parcelable