package com.markvtls.feature_cart.data.repository

import com.markvtls.feature_cart.data.source.CartApiService
import com.markvtls.feature_cart.domain.model.CartInfo
import com.markvtls.feature_cart.domain.repository.CartRepository
import javax.inject.Inject

/**Implementation of CartRepository.*/
internal class CartRepositoryImpl @Inject constructor(
    private val cartApi: CartApiService
) : CartRepository {

    override suspend fun getCartInfo(): CartInfo {
        return cartApi.getCart()
    }

}