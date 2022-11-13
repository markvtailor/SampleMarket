package com.markvtls.feature_main_screen.domain.use_cases

import com.markvtls.feature_main_screen.domain.model.HotSale
import com.markvtls.feature_main_screen.domain.model.StockResponse
import com.markvtls.feature_main_screen.domain.repository.MarketStockRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

internal class GetHotSalesUseCase @Inject constructor(
    private val repository: MarketStockRepository
) {
    operator fun invoke(): Flow<List<HotSale>> = flow {
        val stock = repository.getStockInfo()
        val hotSales = repository.getHotSales(stock)
        emit(hotSales)
    }
}