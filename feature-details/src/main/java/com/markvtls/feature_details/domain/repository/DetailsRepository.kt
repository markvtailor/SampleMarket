package com.markvtls.feature_details.domain.repository

import com.markvtls.feature_details.domain.model.StockItemDetails

internal interface DetailsRepository {

    suspend fun getDetails(): StockItemDetails
}