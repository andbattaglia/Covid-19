package com.battagliandrea.appdata.model

abstract class DpcData {
    abstract val data: String
    abstract val nation: String
    abstract val intensiveCare: Long
    abstract val hospitalized: Long
    abstract val totalHospitalized: Long
    abstract val homeQuarantined: Long
    abstract val activeCases: Long
    abstract val changeActiveCases: Long
    abstract val newCases: Long
    abstract val totalRecovered: Long
    abstract val totalDeath: Long
    abstract val total: Long
    abstract val test: Long
}