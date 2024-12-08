package com.mohammad_ahmadvand.sample_kotlin_mvvm_rx_retrofit.data.network

import com.mohammad_ahmadvand.sample_kotlin_mvvm_rx_retrofit.ApiService
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://api.example.com"

    val api: ApiService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()
        .create(ApiService::class.java)
}