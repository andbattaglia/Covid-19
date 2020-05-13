package com.battagliandrea.covid19.ui.listdialog

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.battagliandrea.covid19.R
import javax.inject.Inject

class ListDialogAdapter @Inject constructor() : ListAdapter<ListDialogItem, ListDialogVH>(RegionChooserDC()) {

    interface DialogItemListener {
        fun onClickListener(item: ListDialogItem)
    }

    private var itemClickListener: DialogItemListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListDialogVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.dialog_list_item, parent, false)
        return ListDialogVH(view)
    }

    override fun onBindViewHolder(holder: ListDialogVH, position: Int) {
        holder.render(getItem(position), itemClickListener)
    }

    fun insertData(data: List<ListDialogItem>) {
        submitList(data.toMutableList())
    }

    fun setItemClickListener(itemClickListener: DialogItemListener?) {
        this.itemClickListener = itemClickListener
    }

    private class RegionChooserDC : DiffUtil.ItemCallback<ListDialogItem>() {

        override fun areContentsTheSame(
            oldItem: ListDialogItem,
            newItem: ListDialogItem
        ) = oldItem == newItem


        override fun areItemsTheSame(
            oldItem: ListDialogItem,
            newItem: ListDialogItem
        ) = oldItem.id == newItem.id
                && oldItem.name == newItem.name
    }
}