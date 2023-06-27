package com.natiqhaciyef.cryptotrackerapp.data.network.module

import com.natiqhaciyef.cryptotrackerapp.domain.repositories.NetworkRepository
import com.natiqhaciyef.cryptotrackerapp.data.network.service.NetworkService
import com.natiqhaciyef.cryptotrackerapp.data.network.source.NetworkDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {
    private val BASE_URL = "https://api.coingecko.com/"

    @Provides
    @Singleton
    fun provideRemoteConfig(): NetworkService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(NetworkService::class.java)



    @Provides
    @Singleton
    fun provideRemoteDataSource(ns: NetworkService) = NetworkDataSource(ns)


    @Provides
    @Singleton
    fun provideRemoteRepository(nds: NetworkDataSource) = NetworkRepository(nds)

}