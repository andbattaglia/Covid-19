package com.battagliandrea.covid19.ui.main

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.battagliandrea.covid19.di.viewmodel.AssistedSavedStateViewModelFactory
import com.battagliandrea.usecase.SyncDpcDailyVariation
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import kotlinx.coroutines.*
import java.net.UnknownHostException
import javax.inject.Inject

open class MainViewModel @AssistedInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val syncDpcDailyVariation: SyncDpcDailyVariation) : ViewModel()
{

    @AssistedInject.Factory
    interface Factory : AssistedSavedStateViewModelFactory<MainViewModel> {
        override fun create(savedStateHandle: SavedStateHandle): MainViewModel
    }


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
