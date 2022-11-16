package com.markvtls.feature_main_screen.presentation.adapters

import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.markvtls.core.ui.RecyclerListItem
import com.markvtls.core.ui.BaseDiffUtil

internal class HorizontalItemAdapter: AsyncListDifferDelegationAdapter<RecyclerListItem>(
    BaseDiffUtil()
) {

    init {
        delegatesManager
            .addDelegate(MainScreenDelegates.hotSaleDelegate)
    }
}