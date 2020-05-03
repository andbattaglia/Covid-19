package com.battagliandrea.covid19.ui.caselist


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

open class CaseListViewModel @Inject constructor(
    private val syncDpcDailyVariation: SyncDpcDailyVariation,
    private val observeDpcDailyVariation: ObserveDpcDailyVariation,
    private val caseItemMapper: CaseItemMapper,
    private val context: Context
) : ViewModel() {

    private val _headerViewState = MutableLiveData<CaseListViewState.Header>()
    val headerViewState: LiveData<CaseListViewState.Header> = _headerViewState

    private val _listViewState = MutableLiveData<CaseListViewState.CasesList>()
    val listViewState: LiveData<CaseListViewState.CasesList> = _listViewState

    private var getDpcDailyVariationJob: Job? = null

    init {

        _headerViewState.value = CaseListViewState.Header(
            title = context.getString(R.string.title_daily),
            description = context.getString(R.string.title_daily)
        )

        _listViewState.value = CaseListViewState.CasesList(
            caseItems = ViewState.Loading(LoadingItem.createDummy())
        )

        observer()
        loadDpcDailyVariation()
    }

    private fun observer(){
        getDpcDailyVariationJob?.cancel()
        getDpcDailyVariationJob = viewModelScope.launch {
            try {
                delay(2000)

                observeDpcDailyVariation().collect {dpc ->

                    _listViewState.value = CaseListViewState.CasesList(
                        caseItems = ViewState.Success(caseItemMapper.formatToCases(dpc))
                    )

                    _headerViewState.value = _headerViewState.value?.copy(
                        description = "${context.getString(R.string.title_daily)} - ${dpc.date}"
                    )
                }

            } catch (e: Exception){
                _listViewState.value = CaseListViewState.CasesList(
                    caseItems = ViewState.Error(data = emptyList(), throwable= RuntimeException(context.getString(R.string.error_generic)))
                )
            }
        }
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
