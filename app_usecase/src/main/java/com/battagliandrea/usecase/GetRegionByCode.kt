package com.battagliandrea.usecase

import com.battagliandrea.domain.entity.RegionEntity
import com.battagliandrea.domain.repository.RegionRepository
import javax.inject.Inject

class GetRegionByCode @Inject constructor(
        private val regionRepository: RegionRepository
){

    suspend operator fun invoke(code: Long) : RegionEntity{
        return regionRepository.get(regionCode= code)
    }
}


