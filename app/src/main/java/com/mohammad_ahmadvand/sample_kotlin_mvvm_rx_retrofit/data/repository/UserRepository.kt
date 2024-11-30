package com.mohammad_ahmadvand.sample_kotlin_mvvm_rx_retrofit.data.repository

import com.mohammad_ahmadvand.sample_kotlin_mvvm_rx_retrofit.data.model.User
import com.mohammad_ahmadvand.sample_kotlin_mvvm_rx_retrofit.data.network.RetrofitInstance
import io.reactivex.rxjava3.core.Single

class UserRepository {
    private val api = RetrofitInstance.api

    fun fetchUser(): Single<User> {
        return api.getUser()
    }
}