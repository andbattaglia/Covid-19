package com.battagliandrea.covid19.ui.dailycases

import com.battagliandrea.covid19.ui.base.ViewState
import com.battagliandrea.covid19.ui.items.base.ListItem

sealed class DailyCasesViewState {

    data class Header(
        val title: String,
        val description: String
    )

    data class CasesList(
        val listViewState: ViewState<List<ListItem>>
//        val onClickHandler: OnClickHandler
    )
}

typealias OnClickHandler = (id: Int) -> Unit



