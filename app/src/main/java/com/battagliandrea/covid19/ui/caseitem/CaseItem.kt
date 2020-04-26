package com.battagliandrea.covid19.ui.caseitem

import android.graphics.Color
import com.battagliandrea.covid19.ui.common.ListItem
import com.battagliandrea.domain.entity.DpcEntity

data class CaseItem(
    override val id: Long = 0,
    val color: Int = 0,
    val cases: Long = 0,
    val casesChange: Long = 0
): ListItem()

fun DpcEntity.formatToCases(): List<CaseItem> {

    val activeCase = CaseItem(
        color = Color.YELLOW,
        cases = this.activeCases,
        casesChange = this.changeActiveCases
    )

    val deathCase = CaseItem(
        color = Color.BLACK,
        cases = this.totalDeath,
        casesChange = this.total
    )

    val recoveredCase = CaseItem(
        color = Color.GREEN,
        cases = this.totalRecovered,
        casesChange = this.total
    )

    val totalCase = CaseItem(
        color = Color.RED,
        cases = this.total,
        casesChange = this.total
    )

    return listOf(activeCase, deathCase, recoveredCase, totalCase)
}