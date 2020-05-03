package com.battagliandrea.covid19.ui.caselist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.battagliandrea.covid19.R
import com.battagliandrea.covid19.ui.common.ListItem
import com.battagliandrea.covid19.ui.models.caseitem.CaseItem
import com.battagliandrea.covid19.ui.models.lodingitem.LoadingItem
import com.battagliandrea.covid19.ui.viewholder.CaseItemVH
import com.battagliandrea.covid19.ui.viewholder.LoadingItemVH
import javax.inject.Inject

class CasesAdapter @Inject constructor() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object{
        const val TYPE_LOADING = 0
        const val TYPE_DEFAULT = 1
    }

//    var onItemClickListener: OnItemClickListener? = null

    private var data: MutableList<ListItem> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            TYPE_DEFAULT -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.view_case_item, parent, false)
                CaseItemVH(view)
            }
            TYPE_LOADING -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.view_loading_item, parent, false)
                LoadingItemVH(view)
            }
            else -> {
                throw RuntimeException("No supported viewType")
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(getItemViewType(position)){
            TYPE_DEFAULT -> (holder as CaseItemVH).render(data[position] as CaseItem)
            TYPE_LOADING -> (holder as LoadingItemVH).render(data[position] as LoadingItem)
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
            is LoadingItem -> TYPE_LOADING
            is CaseItem -> TYPE_DEFAULT
            else -> -1
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //          UTILS
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    fun updateList(data: List<ListItem>){
        if(this.data != data){
//            val diffCallback = BeersDiffUtils(this.data, data)
//            val diffResult = DiffUtil.calculateDiff(diffCallback)
            this.data.clear()
            this.data.addAll(data)
//            diffResult.dispatchUpdatesTo(this)
            notifyDataSetChanged()
        }
    }
}