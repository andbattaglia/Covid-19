package com.battagliandrea.covid19.ui.main


import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.battagliandrea.covid19.R
import com.battagliandrea.covid19.ui.base.ViewState
import com.battagliandrea.covid19.ui.models.caseitem.CaseItemMapper
import com.battagliandrea.covid19.ui.models.lodingitem.LoadingItem
import com.battagliandrea.usecase.SyncDpcDailyVariation
import com.battagliandrea.usecase.ObserveDpcDailyVariation
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import java.lang.Exception
import java.lang.RuntimeException
import javax.inject.Inject

open class MainViewModel @Inject constructor(
    private val syncDpcDailyVariation: SyncDpcDailyVariation) : ViewModel() {


    init {

        loadDpcDailyVariation()
    }

    private fun loadDpcDailyVariation(){
        viewModelScope.launch {
            syncDpcDailyVariation()
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}
