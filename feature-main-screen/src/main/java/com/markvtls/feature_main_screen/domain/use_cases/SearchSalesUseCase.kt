package com.markvtls.feature_main_screen.domain.use_cases

import com.markvtls.feature_main_screen.domain.model.BestSale
import com.markvtls.feature_main_screen.domain.repository.MarketStockRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**Use this to search items by name.*/
internal class SearchSalesUseCase @Inject constructor(
    private val repository: MarketStockRepository
) {
    operator fun invoke(searchRequest: String): Flow<List<BestSale>> = flow {
        val stock = repository.getStockInfo()
        val sales = repository.getBestSales(stock)
        val filtered = sales.filter { it.title.contains(searchRequest, true) }
        emit(filtered)
    }
}