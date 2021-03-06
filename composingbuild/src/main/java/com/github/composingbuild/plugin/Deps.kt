package com.github.composingbuild.plugin

/**
 * 一些常用的第三方依赖
 */
object Deps {
    //okhttp
    val okhttp = "com.squareup.okhttp3:okhttp:4.9.1"

    //retrofit
    val retrofit = "com.squareup.retrofit2:retrofit:2.9.0"
    val adapter_rxjava2 = "com.squareup.retrofit2:adapter-rxjava2:latest.version"
    val adapter_rxjava3 = "com.squareup.retrofit2:adapter-rxjava3:latest.version"
    val converter_gson = "com.squareup.retrofit2:converter-gson:2.9.0"
    val converter_moshi = "com.squareup.retrofit2:converter-moshi:latest.version"
    val converter_simplexml = "com.squareup.retrofit2:converter-simplexml:latest.version"

    //rxjava
    val rxjava3 = "io.reactivex.rxjava3:rxjava:3.0.13"

    //rxandroid
    val rxandroid = "io.reactivex.rxjava3:rxandroid:3.0.0"

    val BRVAH = "com.github.CymChad:BaseRecyclerViewAdapterHelper:3.0.4"

    //smart_refresh_layout
    val refresh_layout_kernel = "com.scwang.smart:refresh-layout-kernel:2.0.3"      //核心必须依赖
    val refresh_header_classics = "com.scwang.smart:refresh-header-classics:2.0.3"    //经典刷新头
    val refresh_footer_classics = "com.scwang.smart:refresh-footer-classics:2.0.3"    //经典加载

    //glide
    val glide = "com.github.bumptech.glide:glide:4.12.0"
    val glideCompiler = "com.github.bumptech.glide:compiler:4.12.0"

    //eventbus
    val eventbus = "org.greenrobot:eventbus:3.2.0"

    //gson
    val gson = "com.google.code.gson:gson:2.8.7"

    //autosize
    val autosize = "me.jessyan:autosize:1.2.1"

    val xxpermissions = "com.github.getActivity:XXPermissions:10.8"

    val mmkv = "com.tencent:mmkv-static:1.2.7"

    val toastutils = "com.github.getActivity:ToastUtils:9.0"
    val aroute = "com.alibaba:arouter-api:1.5.1"
    val aroute_processor = "com.alibaba:arouter-compiler:1.5.1"
    val agentweb = "com.just.agentweb:agentweb-androidx:4.1.3"
    val leakcanary = "com.squareup.leakcanary:leakcanary-android:2.6"

    //防止数据倒灌
    val unpeek_livedata = "com.kunminx.arch:unpeek-livedata:7.2.0-beta1"
}