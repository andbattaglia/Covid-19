package com.battagliandrea.covid19.ui.regions

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.battagliandrea.covid19.R
import com.battagliandrea.covid19.ui.items.base.ListItem
import com.battagliandrea.covid19.ui.items.caseitem.CaseItem
import com.battagliandrea.covid19.ui.items.lodingitem.LoadingItem
import com.battagliandrea.covid19.ui.items.caseitem.CaseItemVH
import com.battagliandrea.covid19.ui.items.lodingitem.LoadingItemVH
import javax.inject.Inject

class RegionsDailyCasesAdapter @Inject constructor() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object{
        const val TYPE_DEFAULT = 1
    }

    private var data: MutableList<ListItem> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            TYPE_DEFAULT -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.view_case_vertical_item, parent, false)
                CaseItemVH(view)
            }
            else -> {
                throw RuntimeException("No supported viewType")
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(getItemViewType(position)){
            TYPE_DEFAULT -> (holder as CaseItemVH).render(data[position] as CaseItem)
            else -> {
                throw RuntimeException("No supported viewType")
            }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun getItemId(position: Int): Long {
        return data[position].id.hashCode().toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return when(data[position]){
            is CaseItem -> TYPE_DEFAULT
            else -> -1
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //          UTILS
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    fun updateList(data: List<ListItem>){
        if(this.data != data){
            this.data.clear()
            this.data.addAll(data)
            notifyDataSetChanged()
        }
    }
}