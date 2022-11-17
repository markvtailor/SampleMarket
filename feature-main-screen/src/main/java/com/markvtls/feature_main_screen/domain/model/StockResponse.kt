package com.markvtls.feature_main_screen.domain.model

import com.squareup.moshi.Json

/**Data class for Market API response.*/
internal data class StockResponse(
    @Json(name = "home_store")
    val hotSale: List<HotSale>,
    @Json(name = "best_seller")
    val bestSale: List<BestSale>
)