package com.battagliandrea.covid19.ui.listdialog

import android.content.Context
import androidx.core.content.ContextCompat
import com.battagliandrea.covid19.R
import com.battagliandrea.domain.entity.DpcVariationEntity
import com.battagliandrea.domain.entity.RegionEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class ListDialogMapper @Inject constructor(
    private val context: Context
) {

    open fun mapFromRegions(regions: List<RegionEntity>): List<ListDialogItem> {
        return regions
            .asSequence()
            .filterNotNull()
            .map { mapFromRegion(it) }
            .toList()
    }

    open fun mapFromRegion(region:RegionEntity): ListDialogItem {
        return ListDialogItem(
            id = region.code,
            name = region.name
        )
    }
}
