package com.battagliandrea.covid19.datasource

import com.battagliandrea.appdata.model.DpcData
import retrofit2.Response
import retrofit2.http.*

interface GithubContentContract {


    @GET("dati-json/dpc-covid19-ita-andamento-nazionale.json")
    suspend fun getDpcs(): Response<List<DpcData>>

}