package com.battagliandrea.covid19.di.module

import androidx.lifecycle.ViewModel
import com.abdroid.jrv.core.android.dagger.ViewModelKey
import com.battagliandrea.covid19.ui.caselist.CaseListViewModel
import com.battagliandrea.covid19.ui.charts.ChartsViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap


@Module
class ViewModelModule {

    @Provides
    @IntoMap
    @ViewModelKey(CaseListViewModel::class)
    fun provideCaseListViewModel(viewModel: CaseListViewModel): ViewModel = viewModel

    @Provides
    @IntoMap
    @ViewModelKey(ChartsViewModel::class)
    fun provideChartsViewModel(viewModel: ChartsViewModel): ViewModel = viewModel
}