package com.cleanarchitecture

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.cleanarchitecture.interactors.services.UserFriendlyExceptionService
import com.cleanarchitecture.utils.PIN_CANNOT_BE_CAST_TO_INT
import com.cleanarchitecture.utils.PIN_IS_BLANK
import com.cleanarchitecture.utils.PIN_SHOULD_CONTAINS_4_DIGITS

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class UserFriendlyExceptionServiceTest {

    lateinit var userFriendlyExceptionService: UserFriendlyExceptionService
    private val context = InstrumentationRegistry.getTargetContext()

    @Before
    fun setUp() {
        userFriendlyExceptionService = UserFriendlyExceptionService(context)
    }

    @Test
    fun properFriendlyTextForPinIsBlank() {
        val throwable = Throwable(PIN_IS_BLANK)
        userFriendlyExceptionService(throwable).test()
            .assertValue(context.getString(R.string.blank_pin_error))
    }

    @Test
    fun properFriendlyTextForPinCannotBeCastToInt() {
        val throwable = Throwable(PIN_CANNOT_BE_CAST_TO_INT)
        userFriendlyExceptionService(throwable).test()
            .assertValue(context.getString(R.string.string_pin_error))
    }

    @Test
    fun properFriendlyTextForPinShouldContains4Digits() {
        val throwable = Throwable(PIN_SHOULD_CONTAINS_4_DIGITS)
        userFriendlyExceptionService(throwable).test()
            .assertValue(context.getString(R.string.should_contains_4_digits_error))
    }
}
