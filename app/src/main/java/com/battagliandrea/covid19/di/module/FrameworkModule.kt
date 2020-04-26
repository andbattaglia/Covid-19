package com.battagliandrea.covid19.di.module

import android.content.Context
import com.battagliandrea.covid19.BuildConfig
import com.battagliandrea.covid19.datasource.GithubContentContract
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
open class FrameworkModule {

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //          GSON
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().create()


    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //          OKHTTP
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Provides
    @Singleton
    fun provideOkHttp(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            logging.level = HttpLoggingInterceptor.Level.BODY
        } else {
            logging.level = HttpLoggingInterceptor.Level.NONE
        }

        return OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()
    }


    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //          RETROFIT
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient) =
            createRetrofit(gson, okHttpClient, "https://raw.githubusercontent.com/pcm-dpc/COVID-19/master/")


    @Provides
    @Singleton
    open fun provideApi(retrofit: Retrofit, context: Context): GithubContentContract = retrofit.create(GithubContentContract::class.java)


    private fun createRetrofit(gson: Gson,
                               okHttpClient: OkHttpClient,
                               endpoint: String): Retrofit {

        return Retrofit.Builder()
                .baseUrl(endpoint)
                .client(okHttpClient)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
    }
}
