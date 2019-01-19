package com.cleanarchitecture.interactors.useCases

import com.cleanarchitecture.entity.User
import com.cleanarchitecture.utils.PIN_CANNOT_BE_CAST_TO_INT
import com.cleanarchitecture.utils.PIN_IS_BLANK
import com.cleanarchitecture.utils.PIN_SHOULD_CONTAINS_4_DIGITS
import io.reactivex.Single
import javax.inject.Inject

class VerifyPinAndGetUserUseCase @Inject constructor(private val getUserUseCase: GetUserUseCase) {

    operator fun invoke(pin: String): Single<User> {
        return Single.create<Int> { emitter ->
            emitter.apply {
                when {
                    pin.isBlank() -> onError(Throwable(PIN_IS_BLANK))
                    pin.toIntOrNull() == null -> onError(Throwable(PIN_CANNOT_BE_CAST_TO_INT))
                    pin.length != 4 -> onError(Throwable(PIN_SHOULD_CONTAINS_4_DIGITS))
                    else -> onSuccess(pin.toInt())
                }
            }
        }.flatMap { getUserUseCase(it) }
    }
}