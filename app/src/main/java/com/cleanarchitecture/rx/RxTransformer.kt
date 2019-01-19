package com.cleanarchitecture.rx

import io.reactivex.*

interface RxTransformer {
    fun main(): Scheduler
    fun io(): Scheduler
    fun computation(): Scheduler

    fun <T> single(): SingleTransformer<T, T> = SingleTransformer {
        it.subscribeOn(io()).observeOn(main())
    }

    fun <T> observable(): ObservableTransformer<T, T> = ObservableTransformer {
        it.subscribeOn(io()).observeOn(main())
    }

    fun <T> flowable(): FlowableTransformer<T, T> = FlowableTransformer {
        it.subscribeOn(io()).observeOn(main())
    }

    fun completable(): CompletableTransformer = CompletableTransformer {
        it.subscribeOn(io()).observeOn(main())
    }
}