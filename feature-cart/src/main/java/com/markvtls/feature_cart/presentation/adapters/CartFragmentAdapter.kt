package com.markvtls.feature_cart.presentation.adapters

import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.markvtls.core.ui.BaseDiffUtil
import com.markvtls.core.ui.RecyclerListItem


/**RecyclerView adapter for CartFragment.*/
internal class CartFragmentAdapter :
    AsyncListDifferDelegationAdapter<RecyclerListItem>(BaseDiffUtil()) {

    init {
        delegatesManager
            .addDelegate(CartFragmentDelegates.cartItemDelegate())
    }
}