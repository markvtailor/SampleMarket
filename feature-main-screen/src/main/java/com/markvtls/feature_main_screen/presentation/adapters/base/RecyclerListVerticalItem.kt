package com.markvtls.feature_main_screen.presentation.adapters.base

import com.markvtls.core.ui.RecyclerListItem

/**Base class for RecyclerView vertical items.*/
internal data class RecyclerListVerticalItem(
    val verticalSales: List<RecyclerListItem>
) : RecyclerListItem {
    override val itemId: Int = verticalSales.hashCode()
}