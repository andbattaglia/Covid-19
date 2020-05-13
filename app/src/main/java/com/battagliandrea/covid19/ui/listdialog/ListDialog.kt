package com.battagliandrea.covid19.ui.listdialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.RecyclerView
import com.battagliandrea.covid19.R


class ListDialog(
    val items: MutableList<ListDialogItem>?,
    var itemClickListener: DialogListener?
) : DialogFragment(){

    class Builder {
        var items: MutableList<ListDialogItem>? = null
            private set
        var itemClickListener: DialogListener? = null
            private set

        fun items(items: MutableList<ListDialogItem>) = apply { this.items = items }
        fun itemClickListener(itemClickListener: DialogListener) = apply { this.itemClickListener = itemClickListener }
        fun build() = ListDialog(items, itemClickListener)
    }

    interface DialogListener {
        fun onClickListener(dialog: Dialog, item: ListDialogItem)
    }

    override fun onStart() {
        super.onStart()
        val dialog = dialog
        val width = ViewGroup.LayoutParams.MATCH_PARENT
        val height = ViewGroup.LayoutParams.WRAP_CONTENT
        dialog?.window?.setLayout(width, height)
        isCancelable = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.dialog_list, container, false)


        val adapter = ListDialogAdapter()
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = adapter

        adapter.insertData(items.orEmpty())
        adapter.setItemClickListener(object : ListDialogAdapter.DialogItemListener{
            override fun onClickListener(item: ListDialogItem) {
                dialog?.let {
                    itemClickListener?.onClickListener(it, item)
                }

            }
        })

        return view
    }

}
