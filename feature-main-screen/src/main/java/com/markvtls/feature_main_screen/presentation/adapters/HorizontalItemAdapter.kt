package com.markvtls.feature_main_screen.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.markvtls.feature_main_screen.domain.model.StockItem
import com.markvtls.feature_main_screen.presentation.adapters.base.BaseDiffUtil

internal class HorizontalItemAdapter: AsyncListDifferDelegationAdapter<StockItem>(BaseDiffUtil()) {

    init {
        delegatesManager
            .addDelegate(MainScreenDelegates.hotSaleDelegate)
    }
}