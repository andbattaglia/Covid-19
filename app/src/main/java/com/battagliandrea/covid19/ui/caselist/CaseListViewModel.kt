package com.battagliandrea.covid19.ui.caselist


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.battagliandrea.covid19.ui.models.caseitem.CaseItem
import com.battagliandrea.covid19.ui.models.caseitem.CaseItemMapper
import com.battagliandrea.usecase.GetDpcDailyVariation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

open class CaseListViewModel @Inject constructor(
    private val getDpcDailyVariation: GetDpcDailyVariation,
    private val caseItemMapper: CaseItemMapper
) : ViewModel() {

    val casesList = MutableLiveData<List<CaseItem>>()

     init {
        load()
    }

    private fun load(){
        GlobalScope.launch (Dispatchers.Main) {
            val cases = withContext(Dispatchers.IO) { getDpcDailyVariation() }
            casesList.postValue(caseItemMapper.formatToCases(cases))
        }
    }

    override fun onCleared() {
        super.onCleared()
    }
}
