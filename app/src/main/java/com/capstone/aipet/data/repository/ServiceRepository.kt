package com.capstone.aipet.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.capstone.aipet.data.remote.api.ApiService
import com.capstone.aipet.data.remote.paging.ServicePaging
import com.capstone.aipet.data.remote.response.service.ItemServices

class ServiceRepository(private val apiService: ApiService) {
        fun getServices(): LiveData<PagingData<ItemServices>> {
            return Pager(
                config = PagingConfig(
                    pageSize = 5
                ),
                pagingSourceFactory = {
                    ServicePaging(apiService)
                }
            ).liveData
        }
}