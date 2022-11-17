package com.markvtls.feature_main_screen.domain.use_cases

import com.markvtls.feature_main_screen.domain.model.StockResponse
import com.markvtls.feature_main_screen.domain.repository.MarketStockRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**Use this to get stock info.*/
internal class GetStockInfoUseCase @Inject constructor(
    private val repository: MarketStockRepository
) {
    suspend operator fun invoke(): Flow<StockResponse> = flow {
        val response = repository.getStockInfo()
        emit(response)
    }
}