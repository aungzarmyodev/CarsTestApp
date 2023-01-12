package com.sevenpeakssoftware.aungzarmyo.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val baseUrl = "https://cars-sevenpeaks.web.app"

    @Provides
    @Singleton
    fun provideNetworkService(): CarApi {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CarApi::class.java)
    }
}
