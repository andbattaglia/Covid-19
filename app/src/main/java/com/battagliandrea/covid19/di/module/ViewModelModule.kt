package com.battagliandrea.covid19.di.module

import androidx.lifecycle.ViewModel
import com.abdroid.jrv.core.android.dagger.ViewModelKey
import com.battagliandrea.covid19.ui.main.MainViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap


@Module
class ViewModelModule {

    @Provides
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun provideMainViewModel(viewModel: MainViewModel): ViewModel = viewModel
}