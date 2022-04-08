package com.github.chenfeichongqing.base.ui

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.github.chenfeichongqing.base.data.model.bean.BillInfo
import com.github.chenfeichongqing.base.network.ApiService


class MainRepoPagingSource(private val mainPageService: ApiService) : PagingSource<Int, BillInfo>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, BillInfo> {
        return try {
            val page = params.key ?: 1
            val pageSize = params.loadSize
            val repoResponse = mainPageService.getBillBypage(page,pageSize).data
            val repoItems = repoResponse.list
            val prevKey = if (page > 1) page - 1 else null
            val nextKey = if (repoResponse.hasNextPage) page + 1 else null
            LoadResult.Page(repoItems, prevKey, nextKey)
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, BillInfo>): Int? = null
}