package com.battagliandrea.covid19.ui.caseitem

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.view_case_item.view.*

class CaseItemVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun render(item: CaseItem) = with(itemView) {

        tvTitle.text = item.title
        tvValue.also {
            it.text = "${item.cases}"
            it.setTextColor(item.casesTextColor)
        }
        tvChangeValue.also {
            it.text = "${item.casesChange}"
            it.setTextColor(item.casesChangeTextColor)
        }

        circleTag.background = item.tag
    }
}