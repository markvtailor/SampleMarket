package com.markvtls.feature_cart.data.source

import com.markvtls.feature_cart.domain.model.CartInfo
import retrofit2.http.GET

internal interface CartApiService {

    @GET("53539a72-3c5f-4f30-bbb1-6ca10d42c149")
    suspend fun getCart(): CartInfo
}