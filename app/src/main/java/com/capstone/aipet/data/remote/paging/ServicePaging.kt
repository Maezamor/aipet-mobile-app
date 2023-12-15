package com.capstone.aipet.data.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.capstone.aipet.data.remote.api.ApiService
import com.capstone.aipet.data.remote.response.dogs.ItemDogs
import com.capstone.aipet.data.remote.response.service.ItemServices

class ServicePaging(private val apiService: ApiService): PagingSource<Int, ItemServices>() {
    private companion object {
        const val INITIAL_PAGE_INDEX = 1
    }
    override fun getRefreshKey(state: PagingState<Int, ItemServices>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ItemServices> {
        return try {
            val page = params.key ?: INITIAL_PAGE_INDEX
            val responseData = apiService.getServices(page, params.loadSize)
            val responseServices = responseData.servicesResponse
            LoadResult.Page(
                data = responseServices.itemServices,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (responseServices.itemServices.isEmpty()) null else page + 1
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }

}