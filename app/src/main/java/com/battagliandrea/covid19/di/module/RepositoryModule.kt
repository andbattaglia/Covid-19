package com.battagliandrea.covid19.di.module

import com.battagliandrea.appdata.repository.DpcRepositoryImpl
import com.battagliandrea.appdata.repository.RegionRepositoryImpl
import com.battagliandrea.domain.repository.DpcRepository
import com.battagliandrea.domain.repository.RegionRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
open class RepositoryModule {

    @Provides
    @Singleton
    fun provideDpcRepository(r: DpcRepositoryImpl): DpcRepository = r

    @Provides
    @Singleton
    fun provideRegionRepositoryImpl(r: RegionRepositoryImpl): RegionRepository = r
}
