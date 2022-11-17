package com.markvtls.feature_main_screen.di

import com.markvtls.feature_main_screen.data.repository.MarketStockRepositoryImpl
import com.markvtls.feature_main_screen.data.source.MarketApiService
import com.markvtls.feature_main_screen.domain.repository.MarketStockRepository
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
    fun provideMarketStockRepository(marketApi: MarketApiService): MarketStockRepository =
        MarketStockRepositoryImpl(marketApi)

}