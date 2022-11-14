package com.markvtls.feature_details.di

import com.markvtls.feature_details.data.repository.DetailsRepositoryImpl
import com.markvtls.feature_details.data.source.DetailsApiService
import com.markvtls.feature_details.domain.repository.DetailsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideDetailsRepository(detailsApi: DetailsApiService): DetailsRepository = DetailsRepositoryImpl(detailsApi)
}