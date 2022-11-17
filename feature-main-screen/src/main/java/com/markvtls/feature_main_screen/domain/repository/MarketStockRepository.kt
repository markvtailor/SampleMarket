package com.markvtls.feature_main_screen.domain.repository

import com.markvtls.feature_main_screen.domain.model.BestSale
import com.markvtls.feature_main_screen.domain.model.CartInfo
import com.markvtls.feature_main_screen.domain.model.HotSale
import com.markvtls.feature_main_screen.domain.model.StockResponse

/**Interface for MarketStockRepository.*/
internal interface MarketStockRepository {

    /**Request stock info from api.*/
    suspend fun getStockInfo(): StockResponse

    /**Get best sales from stock info.*/
    suspend fun getBestSales(stockInfo: StockResponse): List<BestSale>

    /**Get hot sales from stock info.*/
    suspend fun getHotSales(stockInfo: StockResponse): List<HotSale>

    /**Get amount of items in a cart.*/
    suspend fun getCartItemsInfo(): CartInfo

}