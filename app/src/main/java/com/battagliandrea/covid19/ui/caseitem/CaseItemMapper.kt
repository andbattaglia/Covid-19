package com.battagliandrea.covid19.ui.caseitem

import android.content.Context
import android.graphics.Color
import com.battagliandrea.covid19.R
import com.battagliandrea.domain.entity.DpcChangesEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class CaseItemMapper @Inject constructor(
    private val context: Context
) {

    open fun formatToCases(dpc: DpcChangesEntity): List<CaseItem> {

        val activeCase = CaseItem(
            color = Color.CYAN,
            cases = dpc.activeCases,
            casesChange = dpc.activeCasesChange,
            title = context.getString(R.string.active_cases)
        )

        val deathCase = CaseItem(
            color = Color.BLACK,
            cases = dpc.death,
            casesChange = dpc.deathChanges,
            title = context.getString(R.string.death_cases)
        )

        val recoveredCase = CaseItem(
            color = Color.GREEN,
            cases = dpc.recovered,
            casesChange = dpc.recoveredChange,
            title = context.getString(R.string.recovered_cass)
        )

        val totalCase = CaseItem(
            color = Color.RED,
            cases = dpc.total,
            casesChange = dpc.totalChanges,
            title = context.getString(R.string.total_cases)
        )

        return listOf(activeCase, deathCase, recoveredCase, totalCase)
    }
}
