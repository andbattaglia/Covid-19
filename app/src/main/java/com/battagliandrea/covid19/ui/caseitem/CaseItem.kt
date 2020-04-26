package com.battagliandrea.covid19.ui.caseitem

import android.graphics.Color
import com.battagliandrea.covid19.ui.common.ListItem
import com.battagliandrea.domain.entity.DpcChangesEntity
import com.battagliandrea.domain.entity.DpcEntity

data class CaseItem(
    override val id: Long = 0,
    val color: Int = 0,
    val cases: Long = 0,
    val casesChange: Long = 0,
    val title: String = ""
): ListItem()

