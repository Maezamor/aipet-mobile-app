package com.capstone.aipet.data.remote.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.capstone.aipet.data.remote.api.ApiService
import com.capstone.aipet.data.remote.response.dogs.ItemDogs
import com.capstone.aipet.data.remote.response.dogs.ResponseDogs

class DogsPaging (private val apiService: ApiService): PagingSource<Int, ItemDogs>() {
    private companion object {
        const val INITIAL_PAGE_INDEX = 1
    }
    override fun getRefreshKey(state: PagingState<Int, ItemDogs>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ItemDogs> {
        return try {
            val page = params.key ?: INITIAL_PAGE_INDEX
            val responseData = apiService.getDogs(page, params.loadSize)
            val responseDogs = responseData.responseDogs
            LoadResult.Page(
                data = responseDogs.listDogs,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (responseDogs.listDogs.isEmpty()) null else page + 1
            )
        } catch (exception: Exception) {
            Log.e("DogsPaging", "Error loading data", exception)
            return LoadResult.Error(exception)
        }
    }

}