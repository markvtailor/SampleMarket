package com.markvtls.feature_main_screen.domain.model

/**Data class for filter options.*/
internal data class FilterOption(
    var brand: String = "",
    val maxPrice: Long = 0,
    val minPrice: Long = 0,
    val maxSize: Double = 0.0,
    val minSize: Double = 0.0
)