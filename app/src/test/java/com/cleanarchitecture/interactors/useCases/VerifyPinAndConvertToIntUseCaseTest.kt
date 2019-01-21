package com.cleanarchitecture.interactors.useCases

import org.junit.Test

class VerifyPinAndConvertToIntUseCaseTest {

    private val verifyPinAndGetUserUseCase = VerifyPinAndConvertToIntUseCase()

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
}