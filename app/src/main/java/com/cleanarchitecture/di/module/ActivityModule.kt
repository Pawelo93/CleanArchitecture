package com.cleanarchitecture.di.module

import com.cleanarchitecture.main.MainActivity
import com.cleanarchitecture.main.MainModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityModule {

    @ContributesAndroidInjector(modules = [MainModule::class])
    fun bindMainActivity(): MainActivity
}