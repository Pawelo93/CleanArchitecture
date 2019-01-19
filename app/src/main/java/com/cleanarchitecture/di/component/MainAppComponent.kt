package com.cleanarchitecture.di.component

import com.cleanarchitecture.app.MainApplication
import com.cleanarchitecture.di.module.ActivityModule
import com.cleanarchitecture.di.module.AppModule
import com.cleanarchitecture.di.module.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    NetworkModule::class,
    ActivityModule::class,
    AndroidSupportInjectionModule::class
])
interface MainAppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(applicatiion: MainApplication): Builder

        fun build(): MainAppComponent
    }

    fun inject(app: MainApplication)
}