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
    suspend operator fun invoke() : Flow<DpcVariationEntity>{

            return dpcRepository.observe()
                .map { dpcs ->
                    val lastDpc = dpcs.last()
                    val beforeLastDpc = dpcs.getOrNull(dpcs.lastIndex - 1)

                    return@map DpcVariationEntity(
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
}


