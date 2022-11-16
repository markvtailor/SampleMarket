package com.markvtls.feature_cart.domain.use_cases

import com.markvtls.feature_cart.domain.model.CartInfo
import com.markvtls.feature_cart.domain.repository.CartRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

internal class GetCartInfoUseCase @Inject constructor(
    private val repository: CartRepository
) {
    suspend operator fun invoke(): Flow<CartInfo> = flow {
        val response = repository.getCartInfo()
        emit(response)
    }
}