package com.markvtls.feature_cart.presentation.adapters

import coil.load
import coil.size.Scale
import coil.transform.RoundedCornersTransformation
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.markvtls.core.ui.RecyclerListItem
import com.markvtls.feature_cart.databinding.CartItemBinding
import com.markvtls.feature_cart.domain.model.CartItem
import java.text.NumberFormat
import java.util.*

internal object CartFragmentDelegates {


    fun cartItemDelegate() = adapterDelegateViewBinding<CartItem, RecyclerListItem, CartItemBinding>(
        {inflater, container -> CartItemBinding.inflate(inflater, container, false)
         } ) {
        bind {
            with(binding) {
                title.text = item.title
                phoneImage.load(item.images) {
                    crossfade(true)
                    transformations(RoundedCornersTransformation())
                }
                price.text = NumberFormat.getCurrencyInstance(Locale.US).format(item.price)
                quantity.text = item.quantity.toString()
            }
        }

    }
}