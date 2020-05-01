package com.battagliandrea.covid19.ui.caseitem

import android.graphics.Color
import android.graphics.drawable.Drawable
import com.battagliandrea.covid19.ui.common.ListItem
import com.battagliandrea.domain.entity.DpcChangesEntity
import com.battagliandrea.domain.entity.DpcEntity

data class CaseItem(
    override val id: Long = 0,
    val tag: Drawable? = null,
    val cases: Long = 0,
    val casesTextColor: Int = 0,
    val casesChange: Long = 0,
    val casesChangeTextColor: Int = 0,
    val title: String = ""
): ListItem()

