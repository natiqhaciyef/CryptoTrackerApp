package com.natiqhaciyef.cryptotrackerapp.data.local.source

import com.natiqhaciyef.cryptotrackerapp.data.local.database.dao.PreviousHistoryDao
import com.natiqhaciyef.cryptotrackerapp.data.models.PriceModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PriceHistoryDataSource(private val dao: PreviousHistoryDao) {

    suspend fun getAllPreviousHistories(): List<PriceModel>? = withContext(Dispatchers.IO){
        dao.getAllPreviousHistories()
    }

    suspend fun getAllPreviousHistoriesByCurrencyId(currencyId: String): List<PriceModel>? = withContext(Dispatchers.IO){
        dao.getAllPreviousHistoriesByCurrencyId(currencyId)
    }

    suspend fun insertPreviousHistory(previousHistoryModel: PriceModel) = withContext(Dispatchers.IO){
        dao.insertPreviousHistory(previousHistoryModel)
    }

    suspend fun deletePreviousHistory(previousHistoryModel: PriceModel) = withContext(Dispatchers.IO){
        dao.deletePreviousHistory(previousHistoryModel)
    }
}