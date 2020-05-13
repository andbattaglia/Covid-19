package com.battagliandrea.covid19.ui.regions

import com.battagliandrea.covid19.ui.base.ViewState
import com.battagliandrea.covid19.ui.items.base.ListItem
import com.battagliandrea.covid19.ui.listdialog.ListDialogItem
import com.battagliandrea.domain.entity.RegionEntity

sealed class RegionsViewState {

    data class RegionChooser(
        val chooserViewState: ViewState<RegionEntity>
    )

    data class CasesList(
        val listViewState: ViewState<List<ListItem>>
//        val onClickHandler: OnClickHandler
    )

    data class RegionsDialog(
        val regions: List<ListDialogItem>
    )
}


