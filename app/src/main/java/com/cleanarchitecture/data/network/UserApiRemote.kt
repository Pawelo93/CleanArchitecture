package com.cleanarchitecture.data.network

import com.cleanarchitecture.entity.User
import io.reactivex.Single
import java.util.concurrent.TimeUnit

class UserApiRemote : UserApi {
    override fun loadUser(pin: Int): Single<User> {
        return Single.just(User("Wacław"))
            .delay(1000, TimeUnit.MILLISECONDS)
    }
}