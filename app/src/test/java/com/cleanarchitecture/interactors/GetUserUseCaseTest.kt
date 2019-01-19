package com.cleanarchitecture.interactors

import com.cleanarchitecture.data.network.UserApi
import com.cleanarchitecture.entity.User
import com.cleanarchitecture.interactors.useCases.GetUserUseCase
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

import org.mockito.Mock
import org.mockito.MockitoAnnotations

class GetUserUseCaseTest {

    lateinit var getUserUseCase: GetUserUseCase

    @Mock
    lateinit var userApi: UserApi

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        getUserUseCase = GetUserUseCase(userApi)
    }

    @Test
    fun `loadUser returns user when api call is success`() {
        val pin = 1234
        val user = User("Wacław")
        whenever(userApi.loadUser(pin)).thenReturn(Single.just(user))

        getUserUseCase(pin).test()
            .assertComplete()
            .assertNoErrors()
            .assertValue(user)
    }

    @Test
    fun `loadUser ends with failure when userApi throws error`() {
        val pin = 1234
        val throwable = Throwable("Error")
        whenever(userApi.loadUser(pin)).thenReturn(Single.error(throwable))

        getUserUseCase(pin).test()
            .assertError(throwable)
    }
}