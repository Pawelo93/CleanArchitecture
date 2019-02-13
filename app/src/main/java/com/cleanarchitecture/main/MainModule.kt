package com.cleanarchitecture.main

import android.arch.lifecycle.ViewModelProviders
import dagger.Module
import dagger.Provides

@Module
class MainModule {

    @Provides
    fun mainView(activity: MainActivity): MainView = activity

    @Provides
    fun viewModel(activity: MainActivity): MainViewModel =
        ViewModelProviders.of(activity).get(MainViewModel::class.java)
}