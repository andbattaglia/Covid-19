package com.battagliandrea.covid19.di.module

import com.battagliandrea.covid19.di.scope.ActivityScope
import com.battagliandrea.covid19.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun mainActivity(): MainActivity

}
