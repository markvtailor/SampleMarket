package com.markvtls.feature_main_screen.presentation.adapters.base

import com.markvtls.core.ui.RecyclerListItem

internal data class RecyclerListHorizontalItem (
    val horizontalSales: List<RecyclerListItem>
): RecyclerListItem {
    override val itemId: Int = horizontalSales.hashCode()
}