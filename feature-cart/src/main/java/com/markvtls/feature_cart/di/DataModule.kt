package com.markvtls.feature_cart.di

import com.markvtls.feature_cart.data.repository.CartRepositoryImpl
import com.markvtls.feature_cart.data.source.CartApiService
import com.markvtls.feature_cart.domain.repository.CartRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class DataModule {

    @Provides
    @Singleton
    fun provideCartRepository(cartApi: CartApiService): CartRepository = CartRepositoryImpl(cartApi)
}