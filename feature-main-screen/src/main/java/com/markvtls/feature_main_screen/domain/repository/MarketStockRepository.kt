package com.markvtls.feature_main_screen.domain.repository

import com.markvtls.feature_main_screen.domain.model.BestSale
import com.markvtls.feature_main_screen.domain.model.CartInfo
import com.markvtls.feature_main_screen.domain.model.HotSale
import com.markvtls.feature_main_screen.domain.model.StockResponse

internal interface MarketStockRepository {

    suspend fun getStockInfo(): StockResponse

    suspend fun getBestSales(stockInfo: StockResponse): List<BestSale>

    suspend fun getHotSales(stockInfo: StockResponse): List<HotSale>

    suspend fun getCartItemsInfo(): CartInfo

}