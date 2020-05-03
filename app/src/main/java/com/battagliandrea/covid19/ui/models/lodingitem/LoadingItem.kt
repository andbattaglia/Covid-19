package com.battagliandrea.covid19.ui.models.lodingitem

import com.battagliandrea.covid19.ui.common.ListItem

data class LoadingItem(
    override val id: Long = 0
): ListItem() {

    companion object{
        fun createDummy(): List<LoadingItem>{
            val list = ArrayList<LoadingItem>()
            for(i in 1..3){
                list.add(LoadingItem())
            }
            return list
        }
    }
}

