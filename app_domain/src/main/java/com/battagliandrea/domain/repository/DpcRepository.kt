package com.battagliandrea.domain.repository

import com.battagliandrea.domain.entity.DpcEntity
import kotlinx.coroutines.flow.Flow


interface DpcRepository {

    suspend fun observe(): Flow<List<DpcEntity>>

    suspend fun sync()

    suspend fun get(): List<DpcEntity>

    suspend fun get(regionCode: Long): List<DpcEntity>
}
