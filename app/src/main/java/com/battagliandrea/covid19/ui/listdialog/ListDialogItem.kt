package com.battagliandrea.covid19.ui.listdialog

import com.battagliandrea.covid19.ui.items.base.ListItem

data class ListDialogItem(
    override val id: Long = 0,
    val name: String = ""
): ListItem()

