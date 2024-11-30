package com.mohammad_ahmadvand.sample_kotlin_mvvm_rx_retrofit.data.network

import com.mohammad_ahmadvand.sample_kotlin_mvvm_rx_retrofit.data.model.User
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface ApiService {
    @GET("MyStore/Test.php")
    fun getUser(): Single<User>
}