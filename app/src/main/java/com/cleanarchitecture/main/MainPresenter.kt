package com.cleanarchitecture.main

import android.arch.lifecycle.Observer
import com.cleanarchitecture.base.BasePresenter
import com.cleanarchitecture.interactors.services.UserFriendlyExceptionService
import com.cleanarchitecture.interactors.useCases.VerifyPinAndGetUserUseCase
import com.cleanarchitecture.rx.RxTransformer
import com.cleanarchitecture.utils.applySchedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class MainPresenter @Inject constructor(
    private val view: MainView,
    private val viewModel: MainViewModel,
    private val verifyPinAdGetUserUseCase: VerifyPinAndGetUserUseCase,
    private val userFriendlyExceptionService: UserFriendlyExceptionService,
    private val rxTransformer: RxTransformer
) : BasePresenter() {

    fun onCreateView() {
        view.loginClicksWithPin
            .throttleFirst(200, TimeUnit.MILLISECONDS, rxTransformer.main())
            .doOnNext { view.hideError() }
            .doOnNext(::loadUser)
            .subscribe()
            .remember()

        viewModel.user.observe(view, Observer {
            if (it != null)
                view.showUser(it)
        })
    }

    private fun loadUser(pin: String) {
        verifyPinAdGetUserUseCase(pin)
            .applySchedulers(rxTransformer)
            .subscribe({ user ->
                viewModel.user.value = user
            }, ::onError)
            .remember()
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
}



