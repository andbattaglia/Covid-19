package com.battagliandrea.covid19.datasource

import com.battagliandrea.appdata.model.DpcData
import com.battagliandrea.appdata.model.DpcNationalData
import com.battagliandrea.appdata.model.DpcRegionData
import retrofit2.Response
import retrofit2.http.*

interface GithubContentContract {


    @GET("dati-json/dpc-covid19-ita-andamento-nazionale.json")
    suspend fun getDpcs(): Response<List<DpcNationalData>>

    @GET("dati-json/dpc-covid19-ita-regioni.json")
    suspend fun getRegionDpcs(): Response<List<DpcRegionData>>
}