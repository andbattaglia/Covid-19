package com.battagliandrea.covid19.ui.regions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.battagliandrea.covid19.ui.base.SingleLiveEvent
import com.battagliandrea.covid19.ui.base.ViewState
import com.battagliandrea.covid19.ui.items.base.ListItem
import com.battagliandrea.covid19.ui.items.caseitem.CaseItemMapper
import com.battagliandrea.covid19.ui.listdialog.ListDialogMapper
import com.battagliandrea.usecase.GetRegions
import com.battagliandrea.usecase.GetRegionByCode
import com.battagliandrea.usecase.GetRegionDpcDailyVariations
import kotlinx.coroutines.*
import java.lang.Exception
import javax.inject.Inject

open class RegionsViewModel @Inject constructor(
    private val getRegions: GetRegions,
    private val getRegionByCode: GetRegionByCode,
    private val getRegionDpcsDaily: GetRegionDpcDailyVariations,

    private val listDialogMapper: ListDialogMapper,
    private val caseItemMapper: CaseItemMapper
) : ViewModel() {

    private val _regionsDialog = SingleLiveEvent<RegionsViewState.RegionsDialog>()
    val regionsDialog: LiveData<RegionsViewState.RegionsDialog> = _regionsDialog

    private val _regionsChooser = MutableLiveData<RegionsViewState.RegionChooser>()
    val regionsChooser: LiveData<RegionsViewState.RegionChooser> = _regionsChooser

    private val _listViewState = MutableLiveData<RegionsViewState.CasesList>()
    val listViewState: LiveData<RegionsViewState.CasesList> = _listViewState

    init {
        _regionsChooser.value = RegionsViewState.RegionChooser(chooserViewState = ViewState.Loading())
        _listViewState.value = RegionsViewState.CasesList(listViewState = ViewState.Success(data = emptyList()))

        selectRegion(code = 1)
    }

    fun selectRegion(code: Long){
        _listViewState.value = _listViewState.value?.copy(listViewState = ViewState.Success(data = emptyList()))

        viewModelScope.launch {
            try{
                val region = withContext(Dispatchers.Default) { getRegionByCode(code = code) }
                _regionsChooser.value = _regionsChooser.value?.copy(chooserViewState = ViewState.Success(data = region))
            } catch (e: NoSuchElementException){
                _regionsChooser.value = _regionsChooser.value?.copy(chooserViewState = ViewState.Error(throwable = e))
            }

            try{
                val dpcs = withContext(Dispatchers.Default) { getRegionDpcsDaily(code = code) }
                val dpc = dpcs.last ()
                _listViewState.value = _listViewState.value?.copy(listViewState = ViewState.Success(caseItemMapper.formatToCases(dpc)))
            } catch (e: Exception){
                _regionsChooser.value = _regionsChooser.value?.copy(chooserViewState = ViewState.Error(throwable = e))
            }
        }
    }

    fun loadRegionsItems(){
        viewModelScope.launch {
            val getRegionsDeferred = async { getRegions() }
            val regions = getRegionsDeferred.await()

            _regionsDialog.value = RegionsViewState.RegionsDialog(regions = listDialogMapper.mapFromRegions(regions = regions))
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}
