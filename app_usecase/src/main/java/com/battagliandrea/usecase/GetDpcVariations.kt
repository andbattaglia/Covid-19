package com.battagliandrea.usecase

import com.battagliandrea.domain.entity.DpcVariationEntity
import com.battagliandrea.domain.repository.DpcRepository
import javax.inject.Inject

class GetDpcVariations @Inject constructor(
        private val dpcRepository: DpcRepository
){

    suspend operator fun invoke() : List<DpcVariationEntity>{
        val dpcs = dpcRepository.get()

        val variations = ArrayList<DpcVariationEntity>()

        dpcs.forEachIndexed { index, dpc ->
            val beforeLastDpc = dpcs.getOrNull(index - 1)
            val variation = DpcVariationEntity(
                activeCases = dpc.activeCases,
                activeCasesChange = dpc.activeCases - (beforeLastDpc?.activeCases ?: 0),
                death = dpc.totalDeath,
                deathChanges = dpc.totalDeath - (beforeLastDpc?.totalDeath ?: 0),
                recovered = dpc.totalRecovered,
                recoveredChange = dpc.totalRecovered - (beforeLastDpc?.totalRecovered ?: 0),
                total = dpc.total,
                totalChanges = dpc.total - (beforeLastDpc?.total ?: 0)
            )

            variations.add(variation)
        }

        return variations
    }
}


