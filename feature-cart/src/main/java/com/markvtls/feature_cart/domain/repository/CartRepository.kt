package com.markvtls.feature_cart.domain.repository

import com.markvtls.feature_cart.domain.model.CartInfo

/**Interface for CartRepository.*/
internal interface CartRepository {

    /**Request cart info from API.*/
    suspend fun getCartInfo(): CartInfo

}