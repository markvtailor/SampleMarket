package com.markvtls.feature_main_screen.data.source

import com.markvtls.feature_main_screen.domain.model.StockResponse
import retrofit2.http.GET

internal interface MarketApiService {

    @GET("654bd15e-b121-49ba-a588-960956b15175")
    suspend fun getStock(): StockResponse

}