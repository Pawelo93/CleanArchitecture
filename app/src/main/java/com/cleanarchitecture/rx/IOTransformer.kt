package com.cleanarchitecture.rx

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class IOTransformer : RxTransformer {
    override fun computation() = Schedulers.computation()

    override fun main() = AndroidSchedulers.mainThread()

    override fun io() = Schedulers.io()
}