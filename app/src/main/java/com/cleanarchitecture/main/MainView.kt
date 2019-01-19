package com.cleanarchitecture.main

import com.cleanarchitecture.entity.User
import io.reactivex.Observable

interface MainView {

    val loginClicksWithPin: Observable<String>

    fun showUser(user: User)

    fun showError(message: String)

    fun hideError()
}