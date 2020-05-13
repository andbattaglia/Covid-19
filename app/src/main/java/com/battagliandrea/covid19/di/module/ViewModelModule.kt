package com.battagliandrea.covid19.di.module

import androidx.lifecycle.ViewModel
import com.abdroid.jrv.core.android.dagger.ViewModelKey
import com.battagliandrea.covid19.ui.dailycases.DailyCasesViewModel
import com.battagliandrea.covid19.ui.main.MainViewModel
import com.battagliandrea.covid19.ui.mainchart.MainChartViewModel
import com.battagliandrea.covid19.ui.regions.RegionsViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap


@Module
class ViewModelModule {

    @Provides
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun provideMainViewModel(viewModel: MainViewModel): ViewModel = viewModel

    @Provides
    @IntoMap
    @ViewModelKey(DailyCasesViewModel::class)
    fun provideDailyCasesViewModel(viewModel: DailyCasesViewModel): ViewModel = viewModel

    @Provides
    @IntoMap
    @ViewModelKey(MainChartViewModel::class)
    fun provideMainChartViewModel(viewModel: MainChartViewModel): ViewModel = viewModel

    @Provides
    @IntoMap
    @ViewModelKey(RegionsViewModel::class)
    fun provideRegionViewModel(viewModel: RegionsViewModel): ViewModel = viewModel
}