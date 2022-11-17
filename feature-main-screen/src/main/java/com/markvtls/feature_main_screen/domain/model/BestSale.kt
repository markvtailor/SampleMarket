package com.markvtls.feature_main_screen.domain.model

import com.markvtls.core.ui.RecyclerListItem
import com.squareup.moshi.Json

/**Data class for "Best sellers" list' items.*/
internal data class BestSale(
    val id: Int,
    @Json(name = "is_favorites")
    val isFavorites: Boolean,
    val title: String,
    @Json(name = "price_without_discount")
    val priceWithoutDiscount: Int,
    @Json(name = "discount_price")
    val discountPrice: Int,
    val picture: String
) : RecyclerListItem {
    override val itemId: Int = id
}