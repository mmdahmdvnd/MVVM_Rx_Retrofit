package com.mohammad_ahmadvand.sample_kotlin_mvvm_rx_retrofit

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.mohammad_ahmadvand.sample_kotlin_mvvm_rx_retrofit.data.model.User
import com.mohammad_ahmadvand.sample_kotlin_mvvm_rx_retrofit.data.repository.UserRepository
import com.mohammad_ahmadvand.sample_kotlin_mvvm_rx_retrofit.ui.UserViewModel
import io.reactivex.rxjava3.core.Single
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*

class UserViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule() // Required for LiveData testing

    private val repository = mock(UserRepository::class.java) // Mocking UserRepository
    private val viewModel = UserViewModel(repository) // Injecting the mocked repository

    private val userObserver = mock(Observer::class.java) as Observer<User>
    private val errorObserver = mock(Observer::class.java) as Observer<String>

    @Test
    fun FetchUser_vm() {
        // Arrange
        val mockUser = User(1, "Jane Doe", "jane.doe@example.com")
        `when`(repository.fetchUser()).thenReturn(Single.just(mockUser))
        viewModel.user.observeForever(userObserver)

        // Act
        viewModel.fetchUser()

        // Assert
        verify(userObserver).onChanged(mockUser)
    }

    @Test
    fun UnFetchUser_vm() {
        // Arrange
        val mockError = RuntimeException("API error")
        `when`(repository.fetchUser()).thenReturn(Single.error(mockError))
        viewModel.error.observeForever(errorObserver)

        // Act
        viewModel.fetchUser()

        // Assert
        mockError.message?.let { verify(errorObserver).onChanged(it) }
    }
}