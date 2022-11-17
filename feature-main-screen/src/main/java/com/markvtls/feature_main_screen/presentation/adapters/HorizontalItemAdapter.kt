package com.markvtls.feature_main_screen.presentation.adapters

import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.markvtls.core.ui.BaseDiffUtil
import com.markvtls.core.ui.RecyclerListItem

/**Adapter for MainScreenFragment horizontal RecyclerView.*/
internal class HorizontalItemAdapter : AsyncListDifferDelegationAdapter<RecyclerListItem>(
    BaseDiffUtil()
) {

    init {
        delegatesManager
            .addDelegate(MainScreenDelegates.hotSaleDelegate())
    }
}