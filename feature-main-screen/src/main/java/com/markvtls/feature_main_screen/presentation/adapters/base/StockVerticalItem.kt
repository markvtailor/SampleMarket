package com.markvtls.feature_main_screen.presentation.adapters.base

import com.markvtls.feature_main_screen.domain.model.StockItem

internal data class StockVerticalItem (
    val verticalSales: List<StockItem>
        ): StockItem