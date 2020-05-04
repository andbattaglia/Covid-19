package com.battagliandrea.covid19.ui.mainchart

import com.battagliandrea.covid19.ui.base.ViewState
import com.battagliandrea.covid19.ui.models.chartvariations.ChartVariations

sealed class MainChartViewState {

    data class Tabs(
        val selected: TabType,
        val chartViewState: ViewState<List<ChartVariations>>
    )

    enum class TabType{
        TOTAL,
        DAILY
    }
}

typealias OnClickHandler = (id: Int) -> Unit



