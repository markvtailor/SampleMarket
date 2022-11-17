package com.markvtls.feature_details.domain.repository

import com.markvtls.feature_details.domain.model.StockItemDetails

/**Interface for DetailsRepository.*/
internal interface DetailsRepository {

    /**Request details from API.*/
    suspend fun getDetails(): StockItemDetails
}