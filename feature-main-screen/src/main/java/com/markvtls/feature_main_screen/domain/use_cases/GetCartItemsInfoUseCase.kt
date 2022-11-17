package com.markvtls.feature_main_screen.domain.use_cases


import com.markvtls.feature_main_screen.domain.repository.MarketStockRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**Use this to get amount of items in a cart.*/
internal class GetCartItemsInfoUseCase @Inject constructor(
    private val repository: MarketStockRepository
) {
    operator fun invoke(): Flow<Int> = flow {
        val cartInfo = repository.getCartItemsInfo()
        emit(cartInfo.basket.size)
    }
}