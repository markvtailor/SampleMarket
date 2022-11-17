package com.markvtls.feature_main_screen.data.source

import com.markvtls.feature_main_screen.domain.model.CartInfo
import com.markvtls.feature_main_screen.domain.model.StockResponse
import retrofit2.http.GET

/**Retrofit client for Market API.*/
internal interface MarketApiService {

    @GET("654bd15e-b121-49ba-a588-960956b15175")
    suspend fun getStock(): StockResponse


    @GET("53539a72-3c5f-4f30-bbb1-6ca10d42c149")
    suspend fun getCartItemsInfo(): CartInfo
}