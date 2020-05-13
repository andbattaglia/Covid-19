package com.battagliandrea.appdata.model

import com.battagliandrea.domain.entity.DpcEntity
import com.battagliandrea.domain.ext.parseDate
import com.google.gson.annotations.SerializedName
import java.util.*

data class DpcRegionData (
    @SerializedName("data") override val data: String = String(),
    @SerializedName("stato") override val nation: String = "",
    @SerializedName("codice_regione") val regionCode: Long = 0,
    @SerializedName("denominazione_regione") val regionName: String = "",
    @SerializedName("lat") val lat: Float = 0f,
    @SerializedName("long") val long: Float = 0f,
    @SerializedName("terapia_intensiva") override val intensiveCare: Long = 0,
    @SerializedName("ricoverati_con_sintomi") override val hospitalized: Long = 0,
    @SerializedName("totale_ospedalizzati") override val totalHospitalized: Long = 0,
    @SerializedName("isolamento_domiciliare") override val homeQuarantined: Long = 0,
    @SerializedName("totale_positivi") override val activeCases: Long = 0,
    @SerializedName("variazione_totale_positivi") override val changeActiveCases: Long = 0,
    @SerializedName("nuovi_positivi") override val newCases: Long = 0,
    @SerializedName("dimessi_guariti") override val totalRecovered: Long = 0,
    @SerializedName("deceduti") override val totalDeath: Long = 0,
    @SerializedName("totale_casi") override val total: Long = 0,
    @SerializedName("tamponi") override val test: Long = 0
): DpcData()

fun List<DpcRegionData>.map(): List<DpcEntity>{
    return this
        .asSequence()
        .filterNotNull()
        .map { it.map() }
        .toList()
}

fun DpcRegionData.map(): DpcEntity{
    return DpcEntity(
        date = data.parseDate(),
        nation = nation,
        regionCode = regionCode,
        regionName = regionName,
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
        test = test
    )
}