package com.markvtls.feature_main_screen.domain.use_cases

import com.markvtls.feature_main_screen.domain.model.BestSale
import com.markvtls.feature_main_screen.domain.model.FilterOption
import com.markvtls.feature_main_screen.domain.repository.MarketStockRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**Use this to filter items.*/
internal class FilterSalesUseCase @Inject constructor(
    private val repository: MarketStockRepository
) {
    operator fun invoke(filterOption: FilterOption): Flow<List<BestSale>> = flow {
        val stock = repository.getStockInfo()
        val sales = repository.getBestSales(stock)

        val filtered: List<BestSale> =
            if (filterOption.brand.isBlank() && filterOption.maxPrice == 0L && filterOption.maxSize == 0.0) {
                sales
            } else if (filterOption.maxPrice == 0L && filterOption.maxSize == 0.0) {
                filterByBrand(sales, filterOption.brand)
            } else if (filterOption.maxPrice == 0L) {
                val filteredByBrand = filterByBrand(sales, filterOption.brand)
                //filterBySize
                filteredByBrand
            } else {
                val filteredByBrand = filterByBrand(sales, filterOption.brand)
                //filterBySize
                val filteredByPrice =
                    filterByPrice(filteredByBrand, filterOption.maxPrice, filterOption.minPrice)
                filteredByPrice
            }

        emit(filtered)
    }

    private fun filterByBrand(salesList: List<BestSale>, brand: String): List<BestSale> {
        return salesList.filter { it.title.contains(brand) }
    }

    private fun filterByPrice(
        salesList: List<BestSale>,
        maxPrice: Long,
        minPrice: Long
    ): List<BestSale> {
        return salesList.filter { sale -> sale.discountPrice in minPrice..maxPrice }
    }

    /**Filtering by size is not implemented yet.*/
    private fun filterBySize(salesList: List<BestSale>, minSize: Double, maxSize: Double) {

    }
}