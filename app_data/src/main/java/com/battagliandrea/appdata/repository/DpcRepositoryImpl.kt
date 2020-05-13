package com.battagliandrea.appdata.repository

import com.battagliandrea.appdata.datasource.GithubFileDataSource
import com.battagliandrea.domain.entity.DpcEntity
import com.battagliandrea.domain.repository.DpcRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
open class DpcRepositoryImpl @Inject constructor(
        private val githubFileDataSource: GithubFileDataSource
) : DpcRepository {

    @ExperimentalCoroutinesApi
    private val dpcChannel: ConflatedBroadcastChannel<List<DpcEntity>> = ConflatedBroadcastChannel()

    @FlowPreview
    @ExperimentalCoroutinesApi
    override suspend fun observe(): Flow<List<DpcEntity>> {
        return dpcChannel.asFlow()
    }

    @ExperimentalCoroutinesApi
    override suspend fun sync() {
        val dpcs = githubFileDataSource.getDpcs()
        dpcChannel.send(dpcs)
    }

    override suspend fun get(): List<DpcEntity> {
        return githubFileDataSource.getDpcs()
    }

    override suspend fun get(regionCode: Long): List<DpcEntity> {
        return githubFileDataSource.getRegionDpcs()
            .filter { it.regionCode == regionCode }
    }
}
