package com.battagliandrea.usecase

import com.battagliandrea.domain.entity.RegionEntity
import com.battagliandrea.domain.repository.RegionRepository
import javax.inject.Inject

class GetRegions @Inject constructor(
        private val regionRepository: RegionRepository
){

    suspend operator fun invoke() : List<RegionEntity>{
        return regionRepository.get()
    }
}


