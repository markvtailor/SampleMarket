package com.markvtls.feature_main_screen.data.repository

import com.markvtls.feature_main_screen.data.source.MarketApiService
import com.markvtls.feature_main_screen.domain.model.BestSale
import com.markvtls.feature_main_screen.domain.model.CartInfo
import com.markvtls.feature_main_screen.domain.model.HotSale
import com.markvtls.feature_main_screen.domain.model.StockResponse
import com.markvtls.feature_main_screen.domain.repository.MarketStockRepository
import javax.inject.Inject


/**Implementation of MarketStockRepository.*/
internal class MarketStockRepositoryImpl @Inject constructor(
    private val marketApi: MarketApiService
) : MarketStockRepository {

    override suspend fun getStockInfo(): StockResponse {
        return marketApi.getStock()
    }

    override suspend fun getBestSales(stockInfo: StockResponse): List<BestSale> {
        return stockInfo.bestSale
    }

    override suspend fun getHotSales(stockInfo: StockResponse): List<HotSale> {
        return stockInfo.hotSale
    }

    override suspend fun getCartItemsInfo(): CartInfo {
        return marketApi.getCartItemsInfo()
    }

}