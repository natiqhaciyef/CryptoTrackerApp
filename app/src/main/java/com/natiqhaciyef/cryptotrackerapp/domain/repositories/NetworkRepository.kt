package com.natiqhaciyef.cryptotrackerapp.domain.repositories

import com.natiqhaciyef.cryptotrackerapp.data.models.CurrencyHistoryModel
import com.natiqhaciyef.cryptotrackerapp.data.models.CurrencyModel
import com.natiqhaciyef.cryptotrackerapp.data.network.source.NetworkDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NetworkRepository(private val nds: NetworkDataSource) {
    suspend fun getAllCryptoCurrencies(
        vs_currency: String = "usd",
        order: String = "market_cap_desc",
        per_page: String = "100",
        page: String = "1",
        sparkline: String = "false",
        locale: String = "en",
    ): List<CurrencyModel>? = nds.getAllCryptoCurrencies(
        vs_currency,
        order,
        per_page,
        page,
        sparkline,
        locale,
    )


    suspend fun getCryptoCurrencyHistory(
        id: String,
        date: String
    ): CurrencyHistoryModel? =
        nds.getCryptoCurrencyHistory(
            id = id,
            date = date
        )


}