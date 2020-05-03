package com.battagliandrea.covid19.ui.models.chartitem

import android.content.Context
import androidx.core.content.ContextCompat
import com.battagliandrea.covid19.R
import com.battagliandrea.domain.entity.DpcVariationEntity
import com.github.mikephil.charting.data.Entry
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class ChartItemMapper @Inject constructor(
    private val context: Context
) {

    open fun formatToChartData(data: List<DpcVariationEntity>?) : List<ChartItem>{
        val valuesActive = ArrayList<Entry>()
        val valuesDeath = ArrayList<Entry>()
        val valuesRecovered = ArrayList<Entry>()
        val valuesTotal = ArrayList<Entry>()

        val valuesActiveVariations = ArrayList<Entry>()
        val valuesDeathVariations = ArrayList<Entry>()
        val valuesRecoveredVariations = ArrayList<Entry>()
        val valuesTotalVariations = ArrayList<Entry>()

        data?.forEachIndexed { index, entity ->
            valuesActive.add(Entry(index.toFloat(), entity.activeCases.toFloat()))
            valuesActiveVariations.add(Entry(index.toFloat(), entity.activeCasesChange.toFloat()))

            valuesDeath.add(Entry(index.toFloat(), entity.death.toFloat()))
            valuesDeathVariations.add(Entry(index.toFloat(), entity.deathChanges.toFloat()))

            valuesRecovered.add(Entry(index.toFloat(), entity.recovered.toFloat()))
            valuesRecoveredVariations.add(Entry(index.toFloat(), entity.recoveredChange.toFloat()))

            valuesTotal.add(Entry(index.toFloat(), entity.total.toFloat()))
            valuesTotalVariations.add(Entry(index.toFloat(), entity.totalChanges.toFloat()))
        }

        val activeItem =
            ChartItem(
                entries = valuesActive,
                entriesVariations = valuesActiveVariations,
                title = context.getString(R.string.active_cases),
                color = ContextCompat.getColor(context, R.color.pink)
            )

        val deathItem = ChartItem(
            entries = valuesDeath,
            entriesVariations = valuesDeathVariations,
            title = context.getString(R.string.death_cases),
            color = ContextCompat.getColor(context, R.color.darker_grey)
        )

        val recoveredItem =
            ChartItem(
                entries = valuesRecovered,
                entriesVariations = valuesRecoveredVariations,
                title = context.getString(R.string.recovered_cass),
                color = ContextCompat.getColor(context, R.color.green)
            )

        val totalItem = ChartItem(
            entries = valuesTotal,
            entriesVariations = valuesTotalVariations,
            title = context.getString(R.string.total_cases),
            color = ContextCompat.getColor(context, R.color.red)
        )

        return listOf(activeItem, deathItem, recoveredItem, totalItem)
    }
}
