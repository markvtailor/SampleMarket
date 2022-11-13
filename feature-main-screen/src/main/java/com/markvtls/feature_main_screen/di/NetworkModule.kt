package com.markvtls.feature_main_screen.di

import com.markvtls.feature_main_screen.data.source.MarketApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
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
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    fun provideMarketApiService(moshi: Moshi): MarketApiService {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl("https://run.mocky.io/v3/")
            .build()
            .create(MarketApiService::class.java)
    }

}