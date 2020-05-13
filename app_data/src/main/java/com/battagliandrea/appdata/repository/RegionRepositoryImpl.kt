package com.battagliandrea.appdata.repository

import com.battagliandrea.domain.entity.RegionEntity
import com.battagliandrea.domain.repository.RegionRepository
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
open class RegionRepositoryImpl @Inject constructor(
) : RegionRepository {

    private val regions = arrayListOf(
        RegionEntity(code = 1, name = "Piemonte"),
        RegionEntity(code = 2, name = "Valle d'Aosta"),
        RegionEntity(code = 3, name = "Lombardia"),
        RegionEntity(code = 5, name = "Veneto"),
        RegionEntity(code = 6, name = "Friuli Venezia Giulia"),
        RegionEntity(code = 7, name = "Liguria"),
        RegionEntity(code = 8, name = "Emilia-Romagna"),
        RegionEntity(code = 9, name = "Toscana"),
        RegionEntity(code = 10, name = "Umbria"),
        RegionEntity(code = 11, name = "Marche"),
        RegionEntity(code = 12, name = "Lazio"),
        RegionEntity(code = 13, name = "Abruzzo"),
        RegionEntity(code = 14, name = "Molise"),
        RegionEntity(code = 15, name = "Campania"),
        RegionEntity(code = 16, name = "Puglia"),
        RegionEntity(code = 17, name = "Basilicata"),
        RegionEntity(code = 18, name = "Calabria"),
        RegionEntity(code = 19, name = "Sicilia"),
        RegionEntity(code = 20, name = "Sardegna")
    )

    override suspend fun get(): List<RegionEntity> {
        return regions
    }

    override suspend fun get(regionCode: Long): RegionEntity {
        return regions.first { regionEntity -> regionEntity.code == regionCode }
    }
}
