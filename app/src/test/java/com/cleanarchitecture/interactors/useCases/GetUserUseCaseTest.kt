package com.cleanarchitecture.interactors.useCases

import com.cleanarchitecture.data.network.UserApi
import com.cleanarchitecture.entity.User
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Test

class GetUserUseCaseTest {

    private val userApi: UserApi = mock()
    private val getUserUseCase = GetUserUseCase(userApi)

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