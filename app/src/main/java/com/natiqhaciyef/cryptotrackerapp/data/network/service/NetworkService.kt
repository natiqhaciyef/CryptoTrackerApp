package com.natiqhaciyef.cryptotrackerapp.data.network.service

import com.natiqhaciyef.cryptotrackerapp.data.models.CurrencyHistoryModel
import com.natiqhaciyef.cryptotrackerapp.data.models.CurrencyModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NetworkService {

//    vs_currency=usd&order=market_cap_desc&per_page=100&page=1&sparkline=false&locale=en

    @GET("api/v3/coins/markets")
    suspend fun getCryptoCurrencies(
        @Query("vs_currency") vs_currency: String = "usd",
        @Query("order") order: String = "market_cap_desc",
        @Query("per_page") per_page: String = "100",
        @Query("page") page: String = "1",
        @Query("sparkline") sparkline: String = "false",
        @Query("locale") locale: String = "en",
    ): List<CurrencyModel>?

    @GET("api/v3/coins/{id}/history")
    suspend fun getCryptoCurrencyHistory(
        @Path("id") id: String = "bitcoin",
        @Query("date") date: String = "30-12-2022",
    ): CurrencyHistoryModel?
}