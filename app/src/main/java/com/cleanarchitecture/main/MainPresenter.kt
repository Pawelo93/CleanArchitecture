package com.cleanarchitecture.main

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.cleanarchitecture.base.BasePresenter
import com.cleanarchitecture.entity.User
import com.cleanarchitecture.interactors.services.UserFriendlyExceptionService
import com.cleanarchitecture.interactors.useCases.VerifyPinAndGetUserUseCase
import com.cleanarchitecture.rx.RxTransformer
import com.cleanarchitecture.utils.applySchedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class MainPresenter(
    private val verifyPinAdGetUserUseCase: VerifyPinAndGetUserUseCase,
    private val userFriendlyExceptionService: UserFriendlyExceptionService,
    private val rxTransformer: RxTransformer
) : BasePresenter() {

    var view: MainView? = null

    fun onCreateView(view: MainView) {
        this.view = view
        view.loginClicksWithPin
            .throttleFirst(200, TimeUnit.MILLISECONDS, rxTransformer.main())
            .doOnNext { view.hideError() }
            .doOnNext(::loadUser)
            .subscribe()
            .remember()
    }

    fun onViewDestroy() {
        view = null
    }

    private fun loadUser(pin: String) {
        verifyPinAdGetUserUseCase(pin)
            .applySchedulers(rxTransformer)
            .subscribe(::onLoadUser, ::onError)
            .remember()
    }

    private fun onLoadUser(user: User) {
        view?.showUser(user)
    }

    private fun onError(throwable: Throwable) {
        userFriendlyExceptionService(throwable)
            .applySchedulers(rxTransformer)
            .subscribe(::onUserFriendlyError)
            .remember()
    }

    private fun onUserFriendlyError(message: String) {
        view?.showError(message)
    }

    class Factory @Inject constructor(
        private val verifyPinAdGetUserUseCase: VerifyPinAndGetUserUseCase,
        private val userFriendlyExceptionService: UserFriendlyExceptionService,
        private val rxTransformer: RxTransformer
    ) : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>) =
            MainPresenter(verifyPinAdGetUserUseCase, userFriendlyExceptionService, rxTransformer) as T
    }
}



