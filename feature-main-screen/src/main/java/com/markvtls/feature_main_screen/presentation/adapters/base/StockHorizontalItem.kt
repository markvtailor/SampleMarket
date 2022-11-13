package com.markvtls.feature_main_screen.presentation.adapters.base

import com.markvtls.feature_main_screen.domain.model.HotSale
import com.markvtls.feature_main_screen.domain.model.StockItem

internal data class StockHorizontalItem (
    val horizontalSales: List<StockItem>
        ): StockItem