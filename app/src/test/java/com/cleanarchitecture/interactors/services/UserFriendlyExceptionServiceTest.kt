package com.cleanarchitecture.interactors.services

import android.content.Context
import android.support.test.runner.AndroidJUnit4
import androidx.test.core.app.ApplicationProvider
import com.cleanarchitecture.R
import com.cleanarchitecture.utils.PIN_CANNOT_BE_CAST_TO_INT
import com.cleanarchitecture.utils.PIN_IS_BLANK
import com.cleanarchitecture.utils.PIN_SHOULD_CONTAINS_4_DIGITS
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UserFriendlyExceptionServiceTest {

    lateinit var userFriendlyExceptionService: UserFriendlyExceptionService
    private val context = ApplicationProvider.getApplicationContext<Context>()

    @Before
    fun setUp() {
        userFriendlyExceptionService = UserFriendlyExceptionService(context)
    }

    @Test
    fun `proper friendly text for pin is blank`() {
        val throwable = Throwable(PIN_IS_BLANK)
        userFriendlyExceptionService(throwable).test()
            .assertValue(context.getString(R.string.blank_pin_error))
    }

    @Test
    fun `proper friendly text for pin cannot be cast to int`() {
        val throwable = Throwable(PIN_CANNOT_BE_CAST_TO_INT)
        userFriendlyExceptionService(throwable).test()
            .assertValue(context.getString(R.string.string_pin_error))
    }

    @Test
    fun `proper friendly text for pin should contains 4 digits`() {
        val throwable = Throwable(PIN_SHOULD_CONTAINS_4_DIGITS)
        userFriendlyExceptionService(throwable).test()
            .assertValue(context.getString(R.string.should_contains_4_digits_error))
    }
}