package com.battagliandrea.covid19.ui.models.chartvariations

import com.github.mikephil.charting.data.Entry

data class ChartVariations(
    val entries: ArrayList<Entry> = arrayListOf(),
    val entriesVariations: ArrayList<Entry> = arrayListOf(),
    val title: String = "",
    val color: Int = 0
)


