package com.battagliandrea.covid19.ui.charts

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.battagliandrea.covid19.R
import com.battagliandrea.covid19.ext.getViewModel
import com.battagliandrea.covid19.ext.observe
import com.battagliandrea.covid19.ui.models.chartitem.ChartItem
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.components.YAxis.AxisDependency
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.google.android.material.tabs.TabLayout
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_chart.*
import javax.inject.Inject


class ChartsFragment : Fragment() {

    private lateinit var mViewModel: ChartsViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_chart, container, false)
        return view
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mViewModel = getViewModel<ChartsViewModel>(viewModelFactory)
        with(mViewModel) {
            observe(chartData){ renderChart(it) }
        }

        setupChart()
        setupTabs()
    }

    private fun setupTabs(){
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab?.position){
                    0 -> mViewModel.showTotalChart()
                    1 -> mViewModel.showDayByDayChart()

                }
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {}

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
        })
    }

    private fun setupChart(){
        val xAxis: XAxis = lineChart.xAxis
        xAxis.isEnabled = false

        val leftAxis: YAxis = lineChart.axisLeft
        leftAxis.isEnabled = false
        leftAxis.typeface = ResourcesCompat.getFont(activity!!, R.font.raleway_medium)
        leftAxis.textColor = ContextCompat.getColor(activity!!, R.color.primaryTextColor)
        leftAxis.setDrawGridLines(true)
        leftAxis.axisLineColor = ContextCompat.getColor(activity!!, R.color.primaryTextLightColor)
        leftAxis.isGranularityEnabled = true

        val rightAxis: YAxis = lineChart.axisRight
        rightAxis.isEnabled = false

        lineChart.description.isEnabled = false
        lineChart.isAutoScaleMinMaxEnabled = true
        lineChart.setPinchZoom(false)
    }

    private fun renderChart(items: List<ChartItem>){

        val sets = ArrayList<LineDataSet>()

        items.forEach { item ->
            val entries = when(item.mode){
                ChartItem.Mode.TOTAL -> item.entries
                ChartItem.Mode.DAILY -> item.entriesVariations
            }

            val set: LineDataSet = LineDataSet(entries, item.title)
            set.axisDependency = AxisDependency.LEFT
            set.color = item.color
            set.setCircleColor(item.color)
            set.lineWidth = 2f
            set.circleRadius = 3f
            set.isHighlightEnabled = false
            set.setDrawCircleHole(false)

            sets.add(set)
        }

        val data = LineData()
        sets.forEach { data.addDataSet(it) }
        data.setValueTextColor(Color.WHITE)
        data.setValueTextSize(9f)

        lineChart.data = data
        lineChart.notifyDataSetChanged()
        lineChart.invalidate()
    }
}
