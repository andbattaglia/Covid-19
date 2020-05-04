package com.battagliandrea.covid19.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.battagliandrea.usecase.SyncDpcDailyVariation
import kotlinx.coroutines.*
import java.net.UnknownHostException
import javax.inject.Inject

open class MainViewModel @Inject constructor(
    private val syncDpcDailyVariation: SyncDpcDailyVariation) : ViewModel() {

    init {
        loadDpcDailyVariation()
    }

    fun loadDpcDailyVariation(){
        viewModelScope.launch {
            try{
                withContext(Dispatchers.Default) {
                    syncDpcDailyVariation()
                }
            } catch (e: UnknownHostException){
                e.printStackTrace()
            }
        }
    }



    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}
