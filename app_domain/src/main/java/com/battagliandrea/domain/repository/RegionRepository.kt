package com.battagliandrea.domain.repository

import com.battagliandrea.domain.entity.RegionEntity

interface RegionRepository {

    suspend fun get(): List<RegionEntity>
    suspend fun get(regionCode: Long): RegionEntity
}
