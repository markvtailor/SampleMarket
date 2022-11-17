package com.markvtls.feature_main_screen.presentation.adapters.base

import com.markvtls.core.ui.RecyclerListItem

/**Base class for RecyclerView horizontal items.*/
internal data class RecyclerListHorizontalItem(
    val horizontalSales: List<RecyclerListItem>
) : RecyclerListItem {
    override val itemId: Int = horizontalSales.hashCode()
}