package com.battagliandrea.covid19.di.module

import com.battagliandrea.appdata.datasource.GithubFileDataSource
import com.battagliandrea.covid19.datasource.GithubFileDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Provide steps-level dependencies.
 */
@Module
open class DataSourceModule {

    @Provides
    @Singleton
    fun provideGithubFileDatasource(ds: GithubFileDataSourceImpl): GithubFileDataSource = ds

}
