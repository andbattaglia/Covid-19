package com.battagliandrea.covid19.datasource


import com.battagliandrea.appdata.datasource.GithubFileDataSource
import com.battagliandrea.appdata.model.map
import com.battagliandrea.domain.entity.DpcEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GithubFileDataSourceImpl @Inject constructor(
    private val githubContentContract: GithubContentContract
) : GithubFileDataSource {

    override suspend fun getDpcs(): List<DpcEntity> {
        return githubContentContract.getDpcs()
            .let {
                it.body()
                ?.map()
                .orEmpty()
            }
    }

    override suspend fun getRegionDpcs(): List<DpcEntity> {
        return githubContentContract.getRegionDpcs()
            .let {
                it.body()
                    ?.map()
                    .orEmpty()
            }
    }
}