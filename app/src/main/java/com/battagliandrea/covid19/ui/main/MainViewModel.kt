package com.battagliandrea.covid19.ui.main


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.battagliandrea.covid19.ui.caseitem.CaseItem
import com.battagliandrea.covid19.ui.caseitem.formatToCases
import com.battagliandrea.usecase.GetLastDpcs
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

open class MainViewModel @Inject constructor(
        private val getLastDpcs: GetLastDpcs
) : ViewModel() {


    val casesList = MutableLiveData<List<CaseItem>>()

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //          PUBLIC METHODS
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    init {
        load()
    }

    private fun load(){
        GlobalScope.launch (Dispatchers.Main) {
            val result = withContext(Dispatchers.IO) { getLastDpcs() }
            casesList.postValue(result.formatToCases())
        }
    }
}
