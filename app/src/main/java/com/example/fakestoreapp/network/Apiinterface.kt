package com.example.fakestoreapp.network

import com.example.fakestoreapp.response.Product
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

   // https://api.escuelajs.co/api/v1/products?offset=0&limit=10

    @GET("products/")
    suspend fun getProducts(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ):List<Product>
}