package com.markvtls.feature_cart.domain.model

internal data class CartInfo (
    val id: Int,
    val basket: List<CartItem>,
    val delivery: String,
    val total: Long
        )