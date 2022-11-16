package com.markvtls.feature_details.di

import com.markvtls.feature_details.domain.repository.DetailsRepository
import com.markvtls.feature_details.domain.use_cases.GetDetailsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(ViewModelComponent::class)
internal class UseCaseModule {

    @Provides
    fun provideGetDetailsUseCase(repository: DetailsRepository) = GetDetailsUseCase(repository)
}