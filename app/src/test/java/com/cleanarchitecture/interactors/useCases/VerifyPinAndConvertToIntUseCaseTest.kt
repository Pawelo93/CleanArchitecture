package com.cleanarchitecture.interactors.useCases

import com.cleanarchitecture.utils.PIN_CANNOT_BE_CAST_TO_INT
import com.cleanarchitecture.utils.PIN_IS_BLANK
import com.cleanarchitecture.utils.PIN_SHOULD_CONTAINS_4_DIGITS
import org.junit.Test

class VerifyPinAndConvertToIntUseCaseTest {

    private val verifyPinAndGetUserUseCase = VerifyPinAndConvertToIntUseCase()

    @Test
    fun `returns pin as int`() {
        val pin = "1234"
        verifyPinAndGetUserUseCase(pin).test()
            .assertNoErrors()
            .assertValue(pin.toInt())
    }

    @Test
    fun `throw error when pin is blank`() {
        verifyPinAndGetUserUseCase("").test()
            .assertErrorMessage(PIN_IS_BLANK)
    }

    @Test
    fun `throw error when pin is not int`() {
        verifyPinAndGetUserUseCase("abcd").test()
            .assertErrorMessage(PIN_CANNOT_BE_CAST_TO_INT)
    }

    @Test
    fun `throw error when pin length is not 4`() {
        verifyPinAndGetUserUseCase("123").test()
            .assertErrorMessage(PIN_SHOULD_CONTAINS_4_DIGITS)
    }
}