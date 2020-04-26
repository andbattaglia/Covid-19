package com.battagliandrea.domain.repository

import com.battagliandrea.domain.entity.DpcEntity


interface DpcRepository {

    suspend fun get(): List<DpcEntity>
}
