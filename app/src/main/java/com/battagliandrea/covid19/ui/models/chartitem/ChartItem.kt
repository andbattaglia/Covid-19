package com.battagliandrea.covid19.ui.models.chartitem

import com.battagliandrea.covid19.ui.common.ListItem
import com.github.mikephil.charting.data.Entry

data class ChartItem(
    override val id: Long = 0,
    val entries: ArrayList<Entry> = arrayListOf(),
    val entriesVariations: ArrayList<Entry> = arrayListOf(),
    val title: String = "",
    val color: Int = 0,
    var mode: Mode = Mode.TOTAL
): ListItem(){

    enum class Mode{
        TOTAL,
        DAILY
    }
}



