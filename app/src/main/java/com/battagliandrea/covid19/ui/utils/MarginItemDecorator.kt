package com.battagliandrea.covid19.ui.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class MarginItemDecorator(
    private val spaceHeight: Int,
    private val orientation: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        with(outRect) {
            when(orientation){
                0 -> {
                    if (parent.getChildAdapterPosition(view) == 0) {
                        left = spaceHeight
                    }
                    top =  spaceHeight
                    right = spaceHeight
                    bottom = spaceHeight
                }
                1 -> {
                    if (parent.getChildAdapterPosition(view) == 0) {
                        top = spaceHeight
                    }
                    left =  spaceHeight * 2
                    right = spaceHeight * 2
                    bottom = spaceHeight
                }
            }


        }
    }
}