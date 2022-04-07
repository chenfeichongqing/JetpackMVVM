package com.github.chenfeichongqing.base.data.model.bean

import java.io.Serializable

/**
 *  分页数据的基类
 */
data class ApiPagerResponse<T>(
    var list: T,
    var pageNum: Int,
    var isFirstPage: Boolean,
    var hasNextPage: Boolean,
    var pages: Int,
    var pageSize: Int,
    var total: Int
) : Serializable {
    /**
     * 数据是否为空
     */
    fun isEmpty() = (list as List<*>).size == 0

    /**
     * 是否为刷新
     */
    fun isRefresh() = isFirstPage

    /**
     * 是否还有更多数据
     */
    fun hasMore() = hasNextPage
}