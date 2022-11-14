package com.markvtls.feature_main_screen.domain.model

import com.squareup.moshi.Json

internal data class BestSale (
    val id: Int,
    @Json(name = "is_favorites")
    val isFavorites: Boolean,
    val title: String,
    @Json(name = "price_without_discount")
    val priceWithoutDiscount: Int,
    @Json(name = "discount_price")
    val discountPrice: Int,
    val picture: String
): StockItem {
    override val itemId: Int = id
}