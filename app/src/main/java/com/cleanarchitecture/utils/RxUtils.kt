package com.cleanarchitecture.utils

import com.cleanarchitecture.rx.RxTransformer
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single

fun <T> Observable<T>.applySchedulers(rxTransformer: RxTransformer): Observable<T> {
    return this.compose(rxTransformer.observable())
}

fun <T> Flowable<T>.applySchedulers(rxTransformer: RxTransformer): Flowable<T> {
    return this.compose(rxTransformer.flowable())
}

fun <T> Single<T>.applySchedulers(rxTransformer: RxTransformer): Single<T> {
    return this.compose(rxTransformer.single())
}

fun Completable.applySchedulers(rxTransformer: RxTransformer): Completable {
    return this.compose(rxTransformer.completable())
}


fun <T> Observable<T>.doOnMain(rxTransformer: RxTransformer): Observable<T> {
    return this.observeOn(rxTransformer.main())
}