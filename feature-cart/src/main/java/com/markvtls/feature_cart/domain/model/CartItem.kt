package com.markvtls.feature_cart.domain.model

import com.markvtls.core.ui.RecyclerListItem

internal data class CartItem (
    val id: Int,
    val images: String,
    val price: Long,
    val title: String,
    var quantity: Int = 1
        ) : RecyclerListItem {
    override val itemId: Int = id
        }