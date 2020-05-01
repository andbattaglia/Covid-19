package com.battagliandrea.covid19.ui.models.caseitem

import android.content.Context
import androidx.core.content.ContextCompat
import com.battagliandrea.covid19.R
import com.battagliandrea.domain.entity.DpcVariationEntity
import com.github.mikephil.charting.data.Entry
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class CaseItemMapper @Inject constructor(
    private val context: Context
) {

    open fun formatToCases(dpc: DpcVariationEntity): List<CaseItem> {

        val activeCase = CaseItem(
            tag = ContextCompat.getDrawable(context, R.drawable.img_circle_pink),
            cases = dpc.activeCases,
            casesTextColor = ContextCompat.getColor(context, R.color.pink),
            casesChange = dpc.activeCasesChange,
            casesChangeTextColor = ContextCompat.getColor(context, R.color.pink_light),
            title = context.getString(R.string.active_cases)
        )

        val recoveredCase = CaseItem(
            tag = ContextCompat.getDrawable(context, R.drawable.img_circle_green),
            cases = dpc.recovered,
            casesTextColor = ContextCompat.getColor(context, R.color.green),
            casesChange = dpc.recoveredChange,
            casesChangeTextColor = ContextCompat.getColor(context, R.color.green_light),
            title = context.getString(R.string.recovered_cass)
        )

        val deathCase = CaseItem(
            tag = ContextCompat.getDrawable(context, R.drawable.img_circle_darker_gray),
            cases = dpc.death,
            casesTextColor = ContextCompat.getColor(context, R.color.darker_grey),
            casesChange = dpc.deathChanges,
            casesChangeTextColor = ContextCompat.getColor(context, R.color.darker_grey_light),
            title = context.getString(R.string.death_cases)
        )

        val totalCase = CaseItem(
            tag = ContextCompat.getDrawable(context, R.drawable.img_circle_red),
            cases = dpc.total,
            casesTextColor = ContextCompat.getColor(context, R.color.red),
            casesChange = dpc.totalChanges,
            casesChangeTextColor = ContextCompat.getColor(context, R.color.red_light),
            title = context.getString(R.string.total_cases)
        )

        return listOf(activeCase, deathCase, recoveredCase, totalCase)
    }
}
