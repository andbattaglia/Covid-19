package com.battagliandrea.covid19.ui.listdialog

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.dialog_list_item.view.*

class ListDialogVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun render(item: ListDialogItem, listener: ListDialogAdapter.DialogItemListener?) = with(itemView) {
        tvName.text = item.name

        row.setOnClickListener { listener?.onClickListener(item) }
    }
}