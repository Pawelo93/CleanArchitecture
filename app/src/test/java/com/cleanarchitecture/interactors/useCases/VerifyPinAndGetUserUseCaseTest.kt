package com.cleanarchitecture.interactors

import com.cleanarchitecture.entity.User
import com.cleanarchitecture.interactors.useCases.GetUserUseCase
import com.cleanarchitecture.interactors.useCases.VerifyPinAndGetUserUseCase
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Before

import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class VerifyPinAndGetUserUseCaseTest {

    lateinit var verifyPinAndGetUserUseCase: VerifyPinAndGetUserUseCase

    @Mock
    lateinit var getUserUseCase: GetUserUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        verifyPinAndGetUserUseCase =
                VerifyPinAndGetUserUseCase(getUserUseCase)
    }

    @Test
    fun `throw error when pin is blank`() {
        verifyPinAndGetUserUseCase("").test()
            .assertErrorMessage("Pin is blank")
    }

    @Test
    fun `throw error when pin is not int`() {
        verifyPinAndGetUserUseCase("abc").test()
            .assertErrorMessage("Pin cannot be cast to int")
    }

    @Test
    fun `throw error when pin length is not 4`() {
        verifyPinAndGetUserUseCase("123").test()
            .assertErrorMessage("Pin should contain 4 digits")
    }

    @Test
    fun `returns user when pin is good`() {
        val pin = "1234"
        val user = User("Test")
        whenever(getUserUseCase(pin.toInt())).thenReturn(Single.just(user))

        verifyPinAndGetUserUseCase(pin).test()
            .assertComplete()
            .assertNoErrors()
            .assertValue(user)
    }
}