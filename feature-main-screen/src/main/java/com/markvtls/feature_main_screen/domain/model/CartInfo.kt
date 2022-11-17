package com.markvtls.feature_main_screen.domain.model

/**Data class for cart items amount response.*/
internal data class CartInfo(
    val id: Int,
    val basket: List<CartItem>,
)