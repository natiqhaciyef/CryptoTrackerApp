package com.natiqhaciyef.cryptotrackerapp.domain.repositories

import com.natiqhaciyef.cryptotrackerapp.common.Resource
import com.natiqhaciyef.cryptotrackerapp.data.models.PriceModel

interface PriceHistoryInterface {
    suspend fun getAllPreviousHistories(): Resource<List<PriceModel>?>

    suspend fun getAllPreviousHistoriesByCurrencyId(currencyId: String): Resource<List<PriceModel>?>

    suspend fun insertPreviousHistory(previousHistoryModel: PriceModel)

    suspend fun deletePreviousHistory(previousHistoryModel: PriceModel)
}