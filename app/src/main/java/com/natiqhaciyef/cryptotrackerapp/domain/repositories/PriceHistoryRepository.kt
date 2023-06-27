package com.natiqhaciyef.cryptotrackerapp.domain.repositories

import com.natiqhaciyef.cryptotrackerapp.data.local.source.PriceHistoryDataSource
import com.natiqhaciyef.cryptotrackerapp.data.models.PriceModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PriceHistoryRepository(private val ds: PriceHistoryDataSource) {

    suspend fun getAllPreviousHistories(): List<PriceModel>? =
        ds.getAllPreviousHistories()

    suspend fun getAllPreviousHistoriesByCurrencyId(currencyId: String): List<PriceModel>? =
        ds.getAllPreviousHistoriesByCurrencyId(currencyId)

    suspend fun insertPreviousHistory(previousHistoryModel: PriceModel) =
        ds.insertPreviousHistory(previousHistoryModel)


    suspend fun deletePreviousHistory(previousHistoryModel: PriceModel) =
        ds.deletePreviousHistory(previousHistoryModel)

}