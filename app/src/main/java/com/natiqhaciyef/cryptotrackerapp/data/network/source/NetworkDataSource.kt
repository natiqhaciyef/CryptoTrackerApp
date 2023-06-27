package com.natiqhaciyef.cryptotrackerapp.data.network.source

import com.natiqhaciyef.cryptotrackerapp.data.models.CurrencyHistoryModel
import com.natiqhaciyef.cryptotrackerapp.data.models.CurrencyModel
import com.natiqhaciyef.cryptotrackerapp.data.network.service.NetworkService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.http.Query

class NetworkDataSource(private val ns: NetworkService) {

    suspend fun getAllCryptoCurrencies(
        vs_currency: String,
        order: String,
        per_page: String,
        page: String,
        sparkline: String,
        locale: String,
    ): List<CurrencyModel>? =
        withContext(Dispatchers.IO) {
            ns.getCryptoCurrencies(
                vs_currency = vs_currency,
                order = order,
                per_page = per_page,
                page = page,
                sparkline = sparkline,
                locale = locale,
            )
        }


    suspend fun getCryptoCurrencyHistory(
        id: String,
        date: String
    ): CurrencyHistoryModel? = withContext(Dispatchers.IO) {
        ns.getCryptoCurrencyHistory(
            id = id,
            date = date
        )
    }
}