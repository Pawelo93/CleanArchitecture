package com.cleanarchitecture.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BasePresenter {

    private val disposable = CompositeDisposable()

    protected fun Disposable.remember() {
        disposable.add(this)
    }
}