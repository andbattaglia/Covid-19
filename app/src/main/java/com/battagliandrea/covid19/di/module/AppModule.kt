package com.battagliandrea.covid19.di.module

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
open class AppModule {

    @Provides
    @Singleton
    open fun provideApplicationContext(app: Application): Context = app

    @Provides
    @Singleton
    open fun provideViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory = factory
}
