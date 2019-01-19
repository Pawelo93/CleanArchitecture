package com.cleanarchitecture.di.module

import android.content.Context
import com.cleanarchitecture.app.MainApplication
import com.cleanarchitecture.rx.IOTransformer
import com.cleanarchitecture.rx.RxTransformer
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    fun applicationContext(app: MainApplication): Context {
        return app
    }

    @Provides
    @Singleton
    fun rxTransformer(): RxTransformer {
        return IOTransformer()
    }
}