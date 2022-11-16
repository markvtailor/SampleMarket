package com.markvtls.feature_cart.di

import com.markvtls.feature_cart.data.source.CartApiService
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


@Module
@InstallIn(SingletonComponent::class)
internal class NetworkModule {

    @Provides
    fun provideCartApiService(moshi: Moshi): CartApiService {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl("https://run.mocky.io/v3/")
            .build()
            .create(CartApiService::class.java)
    }
}