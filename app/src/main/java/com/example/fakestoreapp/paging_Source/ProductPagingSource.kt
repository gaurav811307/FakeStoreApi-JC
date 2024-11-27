package com.example.fakestoreapp.paging_Source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.fakestoreapp.network.ApiInterface
import com.example.fakestoreapp.response.Product

class ProductPagingSource(private val apiInterface: ApiInterface):PagingSource<Int, Product>() {
    override fun getRefreshKey(state: PagingState<Int, Product>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?:state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Product> {
        val page = params.key ?: 0
        return try {
            val response = apiInterface.getProducts(page, 10)
            LoadResult.Page(
                data = response,
                prevKey = if (page == 0) null else page -1,
                nextKey = if (response.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}