package com.markvtls.feature_main_screen.presentation.adapters

import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.markvtls.core.ui.RecyclerListItem
import com.markvtls.core.ui.BaseDiffUtil

internal class MainScreenAdapter(toItemDetails: () -> Unit): AsyncListDifferDelegationAdapter<RecyclerListItem>(
    BaseDiffUtil()
) {

    init {
        delegatesManager
            .addDelegate(MainScreenDelegates.horizontalRecyclerListItemDelegate)
            .addDelegate(MainScreenDelegates.verticalStockItemDelegate(toItemDetails = toItemDetails))

    }

}