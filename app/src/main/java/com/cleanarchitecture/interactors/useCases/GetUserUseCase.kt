package com.cleanarchitecture.interactors.useCases

import com.cleanarchitecture.data.network.UserApi
import com.cleanarchitecture.entity.User
import io.reactivex.Single
import javax.inject.Inject

class GetUserUseCase @Inject constructor(private val userApi: UserApi) {

    operator fun invoke(pin: Int): Single<User> {
        return userApi.loadUser(pin)
    }
}