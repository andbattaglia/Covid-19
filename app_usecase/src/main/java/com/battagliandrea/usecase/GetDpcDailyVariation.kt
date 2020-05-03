package com.battagliandrea.usecase

import com.battagliandrea.domain.entity.DpcVariationEntity
import com.battagliandrea.domain.ext.ddMMyyyy
import com.battagliandrea.domain.repository.DpcRepository
import javax.inject.Inject

class GetDpcDailyVariation @Inject constructor(
        private val dpcRepository: DpcRepository
){

    suspend operator fun invoke() : DpcVariationEntity{
        val dpcs = dpcRepository.get()

        val lastDpc = dpcs.last()
        val beforeLastDpc = dpcs.getOrNull(dpcs.lastIndex - 1)

        return DpcVariationEntity(
            date = lastDpc.date.ddMMyyyy(),
            activeCases = lastDpc.activeCases,
            activeCasesChange = lastDpc.activeCases - (beforeLastDpc?.activeCases ?: 0),
            death = lastDpc.totalDeath,
            deathChanges = lastDpc.totalDeath - (beforeLastDpc?.totalDeath ?: 0),
            recovered = lastDpc.totalRecovered,
            recoveredChange = lastDpc.totalRecovered - (beforeLastDpc?.totalRecovered ?: 0),
            total = lastDpc.total,
            totalChanges = lastDpc.total - (beforeLastDpc?.total ?: 0)
        )
    }
}


