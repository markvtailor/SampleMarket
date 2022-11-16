package com.markvtls.feature_cart.domain.use_cases

import com.markvtls.feature_cart.domain.model.CartItem
import com.markvtls.feature_cart.domain.repository.CartRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

internal class GetCartItemsUseCase @Inject constructor(
    private val repository: CartRepository
) {
    suspend operator fun invoke(): Flow<List<CartItem>> = flow {
        val response = repository.getCartInfo()
        emit(response.basket)
    }
}