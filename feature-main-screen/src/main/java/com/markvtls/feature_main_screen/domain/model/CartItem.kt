package com.markvtls.feature_main_screen.domain.model

/**Data class for cart item.*/
internal data class CartItem(
    val id: Int,
    val images: String,
    val price: Long,
    val title: String,
)