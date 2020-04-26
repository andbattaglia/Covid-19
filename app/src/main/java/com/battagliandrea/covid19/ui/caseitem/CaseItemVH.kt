package com.battagliandrea.covid19.ui.caseitem

import android.graphics.drawable.GradientDrawable
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.view_case_item.view.*

class CaseItemVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun render(item: CaseItem) = with(itemView) {

        tvTitle.text = "${item.cases}"
        tvDescription.text = "${+item.casesChange}"

        val bgShape = (circleTag.background as GradientDrawable);
        bgShape.setColor(item.color);
    }
}