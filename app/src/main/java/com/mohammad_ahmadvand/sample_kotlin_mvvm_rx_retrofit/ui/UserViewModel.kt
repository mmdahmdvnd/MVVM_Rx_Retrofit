package com.mohammad_ahmadvand.sample_kotlin_mvvm_rx_retrofit.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mohammad_ahmadvand.sample_kotlin_mvvm_rx_retrofit.data.repository.UserRepository
import com.mohammad_ahmadvand.sample_kotlin_mvvm_rx_retrofit.data.model.User
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class UserViewModel : ViewModel() {
    private val repository = UserRepository()
    private val compositeDisposable = CompositeDisposable()

    private val _user = MutableLiveData<User>()
    val user: LiveData<User> get() = _user

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun fetchUser() {
        val disposable = repository.fetchUser()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result -> _user.value = result },
                { throwable -> _error.value = throwable.message }
            )
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}