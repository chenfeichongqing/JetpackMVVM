package com.github.chenfeichongqing.base.ui

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.github.chenfeichongqing.base.data.model.bean.BillInfo
import com.github.chenfeichongqing.base.network.apiService
import kotlinx.coroutines.flow.Flow

object Repository {

    private const val PAGE_SIZE = 20

    fun getBillPagingData(): Flow<PagingData<BillInfo>> {
        return Pager(
            config = PagingConfig(PAGE_SIZE),
            pagingSourceFactory = { MainRepoPagingSource(apiService) }
        ).flow
    }

}