package com.battagliandrea.covid19.ui.mainchart


import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.battagliandrea.covid19.R
import com.battagliandrea.covid19.ui.base.ViewState
import com.battagliandrea.covid19.ui.models.chartvariations.ChartItemMapper
import com.battagliandrea.usecase.ObserveDpcDailyVariation
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

open class MainChartViewModel @Inject constructor(
    private val observeDpcDailyVariation: ObserveDpcDailyVariation,
    private val chartItemMapper: ChartItemMapper,
    private val context: Context
) : ViewModel() {

    private val _tabsViewState = MutableLiveData<MainChartViewState.Tabs>()
    val tabsViewState: LiveData<MainChartViewState.Tabs> = _tabsViewState

    private var getDpcDailyVariationJob: Job? = null

    init {
        _tabsViewState.value = MainChartViewState.Tabs(
            selected = MainChartViewState.TabType.TOTAL,
            chartViewState = ViewState.Loading()
        )

        observer()
    }

    private fun observer(){
        getDpcDailyVariationJob?.cancel()
        getDpcDailyVariationJob = viewModelScope.launch {
            try {
                delay(2000)

                observeDpcDailyVariation().collect { dpcs ->

                    _tabsViewState.value = _tabsViewState.value?.copy(
                        chartViewState = ViewState.Success(chartItemMapper.formatToChartData(dpcs))
                    )
                }

            } catch (e: Exception){
                _tabsViewState.value = _tabsViewState.value?.copy(
                    chartViewState = ViewState.Error(data = emptyList(), throwable= RuntimeException(context.getString(R.string.error_generic)))
                )
            }
        }
    }

    fun showTotalChart(){
        _tabsViewState.value = _tabsViewState.value?.copy(
            selected = MainChartViewState.TabType.TOTAL
        )
    }

    fun showDayByDayChart(){
        _tabsViewState.value = _tabsViewState.value?.copy(
            selected = MainChartViewState.TabType.DAILY
        )
    }


    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}





