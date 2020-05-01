package com.battagliandrea.covid19.ui.charts


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.battagliandrea.covid19.ui.models.caseitem.CaseItemMapper
import com.battagliandrea.covid19.ui.models.chartitem.ChartItem
import com.battagliandrea.covid19.ui.models.chartitem.ChartItemMapper
import com.battagliandrea.usecase.GetDpcVariations
import com.github.mikephil.charting.data.Entry
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

open class ChartsViewModel @Inject constructor(
        private val getDpcsVariations: GetDpcVariations,
        private val chartItemMapper: ChartItemMapper
) : ViewModel() {

    val chartData = MutableLiveData<List<ChartItem>>()

     init {
        load()
    }

    private fun load(){
        GlobalScope.launch (Dispatchers.Main) {
            val dpcs = withContext(Dispatchers.IO) { getDpcsVariations() }
            chartData.postValue(chartItemMapper.formatToChartData(dpcs))
        }
    }

    fun showTotalChart(){
        changeMode(ChartItem.Mode.TOTAL)
    }

    fun showDayByDayChart(){
        changeMode(ChartItem.Mode.DAILY)
    }

    private fun changeMode(mode: ChartItem.Mode){
        GlobalScope.launch (Dispatchers.Main) {
            chartData.value?.let { data ->
                data.forEach {
                    it.mode = mode
                }
                chartData.postValue(data)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()

    }
}
