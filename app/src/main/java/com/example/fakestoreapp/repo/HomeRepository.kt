package com.example.fakestoreapp.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.fakestoreapp.network.ApiInterface
import com.example.fakestoreapp.paging_Source.ProductPagingSource
import com.example.fakestoreapp.response.Product
import javax.inject.Inject

class HomeRepository @Inject constructor(private val apiInterface: ApiInterface) {

    fun getProducts(): Pager<Int, Product> = Pager(
        config = PagingConfig(pageSize = 10, enablePlaceholders = false),
        pagingSourceFactory = { ProductPagingSource(apiInterface) }
    )
}