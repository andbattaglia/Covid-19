package com.battagliandrea.domain.entity

import java.util.*

data class DpcEntity (
    val date: Date = Date(),
    val nation: String = "",
    val regionCode: Long = 0,
    val regionName: String = "",
    val intensiveCare: Long = 0,
    val hospitalized: Long = 0,
    val totalHospitalized: Long = 0,
    val homeQuarantined: Long = 0,
    val activeCases: Long = 0,
    val changeActiveCases: Long = 0,
    val newCases: Long = 0,
    val totalRecovered: Long = 0,
    val totalDeath: Long = 0,
    val total: Long = 0,
    val test: Long = 0,
    val testedCase: Long = 0
)