package com.markvtls.feature_main_screen.domain.use_cases

import com.markvtls.feature_main_screen.domain.model.BestSale
import com.markvtls.feature_main_screen.domain.repository.MarketStockRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**Use this to get best sales.*/
internal class GetBestSalesUseCase @Inject constructor(
    private val repository: MarketStockRepository
) {
    operator fun invoke(): Flow<List<BestSale>> = flow {
        val stock = repository.getStockInfo()
        val bestSales = repository.getBestSales(stock)
        emit(bestSales)
    }
}