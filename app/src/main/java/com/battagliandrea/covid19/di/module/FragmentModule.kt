package com.battagliandrea.covid19.di.module

import com.battagliandrea.covid19.di.scope.FragmentScope
import com.battagliandrea.covid19.ui.main.MainFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun mainFragment(): MainFragment
}
