package com.cleanarchitecture.data.network

import com.cleanarchitecture.entity.User
import io.reactivex.Single

interface UserApi {
    fun loadUser(pin: Int) : Single<User>
}