package com.cleanarchitecture.main

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.cleanarchitecture.base.BasePresenter
import com.cleanarchitecture.entity.User
import com.cleanarchitecture.interactors.services.UserFriendlyExceptionService
import com.cleanarchitecture.interactors.useCases.VerifyPinAndGetUserUseCase
import com.cleanarchitecture.rx.RxTransformer
import com.cleanarchitecture.utils.applySchedulers
import com.cleanarchitecture.utils.doOnMain
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import kotlin.system.measureNanoTime
import kotlin.system.measureTimeMillis

class MainPresenter(
    private val verifyPinAndGetUserUseCase: VerifyPinAndGetUserUseCase,
    private val userFriendlyExceptionService: UserFriendlyExceptionService,
    private val view: MainView,
    private val rxTransformer: RxTransformer
) : BasePresenter() {

    init {
        view.loginClicksWithPin
            .throttleFirst(200, TimeUnit.MILLISECONDS, rxTransformer.io())
            .doOnNext { view.hideError() }
            .flatMapSingle(::loadUser)
            .doOnMain(rxTransformer)
            .subscribe(::onLoadUser, ::onError)
            .remember()
    }

    private fun loadUser(pin: String) = verifyPinAndGetUserUseCase(pin).applySchedulers(rxTransformer)

    private fun onLoadUser(user: User) {
        view.showUser(user)
    }

    private fun onError(throwable: Throwable) {
        userFriendlyExceptionService(throwable)
            .applySchedulers(rxTransformer)
            .subscribe(::onUserFriendlyError)
            .remember()
    }

    private fun onUserFriendlyError(message: String) {
        view.showError(message)
    }

    class Factory @Inject constructor(
        private val verifyPinAndGetUserUseCase: VerifyPinAndGetUserUseCase,
        private val userFriendlyExceptionService: UserFriendlyExceptionService,
        private val view: MainActivity,
        private val rxTransformer: RxTransformer
    ) : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>) =
            MainPresenter(verifyPinAndGetUserUseCase, userFriendlyExceptionService, view, rxTransformer) as T
    }
}



