package com.battagliandrea.covid19.ui.caselist

import com.battagliandrea.covid19.ui.base.ViewState
import com.battagliandrea.covid19.ui.common.ListItem
import com.battagliandrea.covid19.ui.models.caseitem.CaseItem

sealed class CaseListViewState {

    data class Header(
        val title: String,
        val description: String
    )

    data class CasesList(
        val caseItems: ViewState<List<ListItem>>
//        val onClickHandler: OnClickHandler
    )
}

typealias OnClickHandler = (id: Int) -> Unit



