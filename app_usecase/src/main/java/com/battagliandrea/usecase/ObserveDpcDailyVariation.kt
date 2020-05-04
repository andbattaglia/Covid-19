package com.battagliandrea.usecase

import com.battagliandrea.domain.entity.DpcVariationEntity
import com.battagliandrea.domain.ext.ddMMyyyy
import com.battagliandrea.domain.repository.DpcRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class ObserveDpcDailyVariation @Inject constructor(
        private val dpcRepository: DpcRepository
){

    @ExperimentalCoroutinesApi
    suspend operator fun invoke() : Flow<List<DpcVariationEntity>>{

            return dpcRepository.observe()
                .map { dpcs ->

                    val variations = ArrayList<DpcVariationEntity>()

                    dpcs.forEachIndexed { index, dpc ->
                        val beforeLastDpc = dpcs.getOrNull(index - 1)
                        val variation = DpcVariationEntity(
                            date = dpc.date.ddMMyyyy(),
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

                    return@map variations
                }
    }
}


