package com.battagliandrea.usecase

import com.battagliandrea.domain.entity.DpcEntity
import com.battagliandrea.domain.repository.DpcRepository
import javax.inject.Inject

class GetLastDpcs @Inject constructor(
        private val dpcRepository: DpcRepository
){
    suspend operator fun invoke() : DpcEntity{
        return dpcRepository.get().last()
    }
}


