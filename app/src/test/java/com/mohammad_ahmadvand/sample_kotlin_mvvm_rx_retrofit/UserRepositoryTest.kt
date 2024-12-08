package com.mohammad_ahmadvand.sample_kotlin_mvvm_rx_retrofit

import com.mohammad_ahmadvand.sample_kotlin_mvvm_rx_retrofit.data.model.User
import com.mohammad_ahmadvand.sample_kotlin_mvvm_rx_retrofit.data.repository.UserRepository
import io.reactivex.rxjava3.core.Single
import org.junit.Test
import org.mockito.Mockito.*

class UserRepositoryTest {

    private val apiService = mock(ApiService::class.java)
    private val repository = UserRepository(apiService) // تزریق ApiService

    @Test
    fun FetchUser_rep() {
        // Arrange
        val mockUser = User(1, "John Doe", "john.doe@example.com")
        `when`(apiService.getUser()).thenReturn(Single.just(mockUser))

        // Act
        val testObserver = repository.fetchUser().test()

        // Assert
        testObserver.assertValue(mockUser)
        testObserver.assertComplete()
        verify(apiService).getUser()
    }

    @Test
    fun UnFetchUser_rep() {
        // Arrange
        val mockError = RuntimeException("API error")
        `when`(apiService.getUser()).thenReturn(Single.error(mockError))

        // Act
        val testObserver = repository.fetchUser().test()

        // Assert
        testObserver.assertError(mockError)
        verify(apiService).getUser()
    }
}