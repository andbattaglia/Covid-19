package com.battagliandrea.covid19.di.viewmodel

import androidx.lifecycle.ViewModel
import com.abdroid.jrv.core.android.dagger.ViewModelKey
import com.battagliandrea.covid19.ui.dailycases.DailyCasesViewModel
import com.battagliandrea.covid19.ui.main.MainViewModel
import com.battagliandrea.covid19.ui.mainchart.MainChartViewModel
import com.battagliandrea.covid19.ui.regions.RegionsViewModel
import com.squareup.inject.assisted.dagger2.AssistedModule
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap


@AssistedModule
@Module(includes=[AssistedInject_BuilderModule::class])
abstract class BuilderModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModelFactory(f: MainViewModel.Factory): AssistedSavedStateViewModelFactory<out ViewModel>

    @Binds
    @IntoMap
    @ViewModelKey(MainChartViewModel::class)
    abstract fun bindMainChartViewModelFactory(f: MainChartViewModel.Factory): AssistedSavedStateViewModelFactory<out ViewModel>

    @Binds
    @IntoMap
    @ViewModelKey(DailyCasesViewModel::class)
    abstract fun bindDailyCasesViewModelFactory(f: DailyCasesViewModel.Factory): AssistedSavedStateViewModelFactory<out ViewModel>

    @Binds
    @IntoMap
    @ViewModelKey(RegionsViewModel::class)
    abstract fun bindRegionsViewModelFactory(f: RegionsViewModel.Factory): AssistedSavedStateViewModelFactory<out ViewModel>
}