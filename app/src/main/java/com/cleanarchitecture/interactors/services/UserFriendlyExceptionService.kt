package com.cleanarchitecture.interactors.services

import android.content.Context
import com.cleanarchitecture.R
import com.cleanarchitecture.utils.PIN_CANNOT_BE_CAST_TO_INT
import com.cleanarchitecture.utils.PIN_IS_BLANK
import com.cleanarchitecture.utils.PIN_SHOULD_CONTAINS_4_DIGITS
import io.reactivex.Single
import javax.inject.Inject

class UserFriendlyExceptionService @Inject constructor(private val context: Context) {

    operator fun invoke(throwable: Throwable): Single<String> {
        return Single.fromCallable<String> {
            when (throwable.message) {
                PIN_IS_BLANK -> context.getString(R.string.blank_pin_error)
                PIN_CANNOT_BE_CAST_TO_INT -> context.getString(R.string.string_pin_error)
                PIN_SHOULD_CONTAINS_4_DIGITS -> context.getString(R.string.should_contains_4_digits_error)
                else -> ""
            }
        }
    }
}