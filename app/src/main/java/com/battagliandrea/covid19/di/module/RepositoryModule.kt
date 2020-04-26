package com.battagliandrea.covid19.di.module

import com.battagliandrea.appdata.repository.DpcRepositoryImpl
import com.battagliandrea.domain.repository.DpcRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
open class RepositoryModule {

    @Provides
    @Singleton
    fun provideBeerRepository(r: DpcRepositoryImpl): DpcRepository = r


}
