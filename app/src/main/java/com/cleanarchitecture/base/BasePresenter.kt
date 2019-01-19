package com.cleanarchitecture.base

import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BasePresenter : ViewModel() {

    private val disposable = CompositeDisposable()

    protected fun Disposable.remember() {
        disposable.add(this)
    }
}