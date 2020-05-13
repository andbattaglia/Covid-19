package com.battagliandrea.appdata.datasource

import com.battagliandrea.domain.entity.DpcEntity


interface GithubFileDataSource {

    suspend fun getDpcs(): List<DpcEntity>

    suspend fun getRegionDpcs(): List<DpcEntity>
}