package com.battagliandrea.covid19.ui.dailycases


import android.content.Context
import androidx.lifecycle.*
import com.battagliandrea.covid19.R
import com.battagliandrea.covid19.di.viewmodel.AssistedSavedStateViewModelFactory
import com.battagliandrea.covid19.ui.base.ViewState
import com.battagliandrea.covid19.ui.items.caseitem.CaseItemMapper
import com.battagliandrea.covid19.ui.items.lodingitem.LoadingItem
import com.battagliandrea.usecase.ObserveDpcDailyVariation
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import java.lang.Exception
import java.lang.RuntimeException
import javax.inject.Inject

open class DailyCasesViewModel @AssistedInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val observeDpcDailyVariation: ObserveDpcDailyVariation,
    private val caseItemMapper: CaseItemMapper,
    private val context: Context
) : ViewModel() {

    @AssistedInject.Factory
    interface Factory : AssistedSavedStateViewModelFactory<DailyCasesViewModel> {
        override fun create(savedStateHandle: SavedStateHandle): DailyCasesViewModel
    }

    private val _headerViewState = MutableLiveData<DailyCasesViewState.Header>()
    val headerViewState: LiveData<DailyCasesViewState.Header> = _headerViewState

    private val _listViewState = MutableLiveData<DailyCasesViewState.CasesList>()
    val listViewState: LiveData<DailyCasesViewState.CasesList> = _listViewState

    private var getDpcDailyVariationJob: Job? = null

    init {

        _headerViewState.value = DailyCasesViewState.Header(
            title = context.getString(R.string.title_daily),
            description = context.getString(R.string.title_daily)
        )

        _listViewState.value = DailyCasesViewState.CasesList(
            listViewState = ViewState.Loading(LoadingItem.createDummy())
        )

        observer()
    }

    private fun observer(){
        getDpcDailyVariationJob?.cancel()
        getDpcDailyVariationJob = viewModelScope.launch {
            try {
                delay(2000)

                observeDpcDailyVariation().collect {dpcs ->

                    val dpc = dpcs.last()

                    _listViewState.value = DailyCasesViewState.CasesList(
                        listViewState = ViewState.Success(caseItemMapper.formatToCases(dpc))
                    )

                    _headerViewState.value = _headerViewState.value?.copy(
                        description = "${context.getString(R.string.title_daily)} - ${dpc.date}"
                    )
                }

            } catch (e: Exception){
                _listViewState.value = DailyCasesViewState.CasesList(
                    listViewState = ViewState.Error(data = emptyList(), throwable= RuntimeException(context.getString(R.string.error_generic)))
                )
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}
