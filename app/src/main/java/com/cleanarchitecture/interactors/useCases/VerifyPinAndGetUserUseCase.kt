package com.cleanarchitecture.interactors.useCases

import com.cleanarchitecture.entity.User
import io.reactivex.Single
import javax.inject.Inject

class VerifyPinAndGetUserUseCase @Inject constructor(
    private val verifyPinAndConvertToIntUseCase: VerifyPinAndConvertToIntUseCase,
    private val getUserUseCase: GetUserUseCase
) {

    operator fun invoke(pin: String): Single<User> =
        verifyPinAndConvertToIntUseCase(pin)
            .flatMap(getUserUseCase::invoke)

}