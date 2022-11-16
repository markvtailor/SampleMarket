package com.markvtls.feature_cart.domain.repository

import com.markvtls.feature_cart.domain.model.CartInfo
import com.markvtls.feature_cart.domain.model.CartItem

internal interface CartRepository {

    suspend fun getCartInfo(): CartInfo

    suspend fun getCartItems(cart: CartInfo): List<CartItem>
}