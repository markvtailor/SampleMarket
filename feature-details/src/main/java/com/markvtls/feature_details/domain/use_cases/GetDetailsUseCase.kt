package com.markvtls.feature_details.domain.use_cases

import com.markvtls.feature_details.domain.model.StockItemDetails
import com.markvtls.feature_details.domain.repository.DetailsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**Use this to get item details.*/
internal class GetDetailsUseCase @Inject constructor(
    private val repository: DetailsRepository
) {
    suspend operator fun invoke(): Flow<StockItemDetails> = flow {
        val response = repository.getDetails()
        emit(response)
    }
}