package com.markvtls.feature_cart.di

import com.markvtls.feature_cart.domain.repository.CartRepository
import com.markvtls.feature_cart.domain.use_cases.GetCartInfoUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal class UseCaseModule {

    @Provides
    fun provideGetCartInfoUseCase(repository: CartRepository) = GetCartInfoUseCase(repository)

}