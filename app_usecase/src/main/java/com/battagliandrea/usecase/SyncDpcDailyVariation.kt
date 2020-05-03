package com.battagliandrea.usecase

import com.battagliandrea.domain.entity.DpcVariationEntity
import com.battagliandrea.domain.ext.ddMMyyyy
import com.battagliandrea.domain.repository.DpcRepository
import javax.inject.Inject

class SyncDpcDailyVariation @Inject constructor(
        private val dpcRepository: DpcRepository
){

    suspend operator fun invoke() = dpcRepository.sync()
}


