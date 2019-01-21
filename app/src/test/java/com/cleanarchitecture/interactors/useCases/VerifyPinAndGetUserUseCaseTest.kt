package com.cleanarchitecture.interactors.useCases

import com.cleanarchitecture.entity.User
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Test

class VerifyPinAndGetUserUseCaseTest {

    val verifyPinAndConvertToIntUseCase: VerifyPinAndConvertToIntUseCase = mock()
    val getUserUseCase: GetUserUseCase = mock()
    val verifyPinAndGetUserUseCase = VerifyPinAndGetUserUseCase(verifyPinAndConvertToIntUseCase, getUserUseCase)

    @Test
    fun `returns user when pin is verified`() {
        val pin = "1234"
        val user = User()
        whenever(verifyPinAndConvertToIntUseCase(pin)).thenReturn(Single.just(pin.toInt()))
        whenever(getUserUseCase(pin.toInt())).thenReturn(Single.just(user))
        verifyPinAndGetUserUseCase(pin).test()
            .assertValue(user)
    }

    @Test
    fun `returns error when pin is wrong`() {
        val pin = "123"
        val throwable = Throwable()
        whenever(verifyPinAndConvertToIntUseCase(pin)).thenReturn(Single.error(throwable))
        verifyPinAndGetUserUseCase(pin).test()
            .assertError(throwable)
    }

    @Test
    fun `returns error when cant get user`() {
        val pin = "1234"
        val throwable = Throwable()
        whenever(verifyPinAndConvertToIntUseCase(pin)).thenReturn(Single.just(pin.toInt()))
        whenever(getUserUseCase(pin.toInt())).thenReturn(Single.error(throwable))
        verifyPinAndGetUserUseCase(pin).test()
            .assertError(throwable)
    }
}