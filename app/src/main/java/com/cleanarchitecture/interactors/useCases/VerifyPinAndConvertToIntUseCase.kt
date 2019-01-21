package com.cleanarchitecture.interactors.useCases

import com.cleanarchitecture.utils.PIN_CANNOT_BE_CAST_TO_INT
import com.cleanarchitecture.utils.PIN_IS_BLANK
import com.cleanarchitecture.utils.PIN_SHOULD_CONTAINS_4_DIGITS
import io.reactivex.Single
import javax.inject.Inject

class VerifyPinAndConvertToIntUseCase @Inject constructor() {

    operator fun invoke(pin: String): Single<Int> {
        return Single.fromCallable {
            when {
                pin.isBlank() -> throw Throwable(PIN_IS_BLANK)
                pin.toIntOrNull() == null -> throw Throwable(PIN_CANNOT_BE_CAST_TO_INT)
                pin.length != 4 -> throw Throwable(PIN_SHOULD_CONTAINS_4_DIGITS)
                else -> pin.toInt()
            }
        }
    }
}
