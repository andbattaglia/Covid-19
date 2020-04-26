package com.battagliandrea.domain.entity

data class DpcChangesEntity (
    val activeCases: Long = 0,
    val activeCasesChange: Long = 0,
    val recovered: Long = 0,
    val recoveredChange: Long = 0,
    val death: Long = 0,
    val deathChanges: Long = 0,
    val total: Long = 0,
    val totalChanges: Long = 0
)