package com.example.fakestoreapp.di

import com.example.fakestoreapp.network.ApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@InstallIn(SingletonComponent::class)
@Module
object DiModule {
    @Provides
    fun provideRRetrofit(): ApiInterface {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = okhttp3.OkHttpClient.Builder().connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS).writeTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(logging).build()

        return Retrofit.Builder()
            .baseUrl("https://api.escuelajs.co/api/v1/")
            .client(client).addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiInterface::class.java)
    }
}