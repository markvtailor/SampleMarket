package com.markvtls.feature_main_screen.domain.model

internal data class CartInfo (
    val id: Int,
    val basket: List<CartItem>,
)