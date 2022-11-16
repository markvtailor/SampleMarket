package com.markvtls.feature_main_screen.di

import com.markvtls.feature_main_screen.domain.repository.MarketStockRepository
import com.markvtls.feature_main_screen.domain.use_cases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(ViewModelComponent::class)
internal class UseCaseModule {
    @Provides
    fun provideGetStockInfoUseCase(repository: MarketStockRepository) = GetStockInfoUseCase(repository)

    @Provides
    fun provideGetHotSalesUseCase(repository: MarketStockRepository) = GetHotSalesUseCase(repository)

    @Provides
    fun provideGetBestSalesUseCase(repository: MarketStockRepository) = GetBestSalesUseCase(repository)

    @Provides
    fun provideFilterSalesUseCase(repository: MarketStockRepository) = FilterSalesUseCase(repository)

    @Provides
    fun provideSearchSalesUseCase(repository: MarketStockRepository) = SearchSalesUseCase(repository)

    @Provides
    fun provideGetCartItemsInfoUseCase(repository: MarketStockRepository) = GetCartItemsInfoUseCase(repository)
}
