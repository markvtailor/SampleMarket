package com.markvtls.feature_main_screen.domain.model

import com.squareup.moshi.Json

internal data class HotSale (
    val id: Int,
    @Json(name = "is_new")
    val isNew: Boolean?,
    val title: String,
    val subtitle: String,
    val picture: String,
    @Json(name = "is_buy")
    val isBuy: Boolean
): StockItem