package com.cleanarchitecture

import com.cleanarchitecture.rx.RxTransformer
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class TestTransformer(private val testScheduler: Scheduler = Schedulers.trampoline()) : RxTransformer {
    override fun computation() = testScheduler

    override fun main() = testScheduler

    override fun io() = testScheduler
}