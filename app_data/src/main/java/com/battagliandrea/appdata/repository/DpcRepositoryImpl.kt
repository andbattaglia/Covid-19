package com.battagliandrea.appdata.repository

import com.battagliandrea.appdata.datasource.GithubFileDataSource
import com.battagliandrea.domain.entity.DpcEntity
import com.battagliandrea.domain.repository.DpcRepository
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
open class DpcRepositoryImpl @Inject constructor(
        private val githubFileDataSource: GithubFileDataSource
) : DpcRepository {

    override suspend fun get(): List<DpcEntity> {
        return githubFileDataSource.getDpcs()
    }
}
