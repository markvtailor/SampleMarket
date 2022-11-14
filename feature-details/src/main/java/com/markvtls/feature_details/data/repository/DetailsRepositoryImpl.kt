package com.markvtls.feature_details.data.repository

import com.markvtls.feature_details.data.source.DetailsApiService
import com.markvtls.feature_details.domain.model.StockItemDetails
import com.markvtls.feature_details.domain.repository.DetailsRepository
import javax.inject.Inject

class DetailsRepositoryImpl @Inject constructor(
    private val detailsApi: DetailsApiService
): DetailsRepository {

    override suspend fun getDetails(): StockItemDetails {
        return detailsApi.getDetails()
    }
}