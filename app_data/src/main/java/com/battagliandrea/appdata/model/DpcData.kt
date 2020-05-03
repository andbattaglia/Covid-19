package com.battagliandrea.appdata.model

import com.battagliandrea.domain.entity.DpcEntity
import com.battagliandrea.domain.ext.parseDate
import com.google.gson.annotations.SerializedName
import java.util.*

data class DpcData (
    @SerializedName("data") val data: String = String(),
    @SerializedName("stato") val nation: String = "",
    @SerializedName("terapia_intensiva") val intensiveCare: Long = 0,
    @SerializedName("ricoverati_con_sintomi") val hospitalized: Long = 0,
    @SerializedName("totale_ospedalizzati") val totalHospitalized: Long = 0,
    @SerializedName("isolamento_domiciliare") val homeQuarantined: Long = 0,
    @SerializedName("totale_positivi") val activeCases: Long = 0,
    @SerializedName("variazione_totale_positivi") val changeActiveCases: Long = 0,
    @SerializedName("nuovi_positivi") val newCases: Long = 0,
    @SerializedName("dimessi_guariti") val totalRecovered: Long = 0,
    @SerializedName("deceduti") val totalDeath: Long = 0,
    @SerializedName("totale_casi") val total: Long = 0,
    @SerializedName("tamponi") val test: Long = 0,
    @SerializedName("casi_testati") val testedCase: Long = 0
)

fun List<DpcData>.map(): List<DpcEntity>{
    return this
        .asSequence()
        .filterNotNull()
        .map { it.map() }
        .toList()
}

fun DpcData.map(): DpcEntity{
    return DpcEntity(
        date = data.parseDate(),
        nation = nation,
        intensiveCare = intensiveCare,
        hospitalized = hospitalized,
        totalHospitalized = totalHospitalized,
        homeQuarantined = homeQuarantined,
        activeCases = activeCases,
        changeActiveCases = changeActiveCases,
        newCases = newCases,
        totalRecovered = totalRecovered,
        totalDeath = totalDeath,
        total = total,
        test = test,
        testedCase = testedCase
    )
}