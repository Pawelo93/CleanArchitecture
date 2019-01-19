package com.cleanarchitecture.di.module

import com.cleanarchitecture.data.network.UserApi
import com.cleanarchitecture.data.network.UserApiRemote
import dagger.Module
import dagger.Provides

@Module
class NetworkModule {

    @Provides
    fun userApi(): UserApi = UserApiRemote()
}