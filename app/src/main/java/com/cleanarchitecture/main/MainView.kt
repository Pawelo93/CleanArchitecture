package com.cleanarchitecture.main

import android.arch.lifecycle.LifecycleOwner
import com.cleanarchitecture.entity.User
import io.reactivex.Observable

interface MainView : LifecycleOwner{

    val loginClicksWithPin: Observable<String>

    fun showUser(user: User)

    fun showError(message: String)

    fun hideError()
}