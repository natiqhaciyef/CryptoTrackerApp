package com.natiqhaciyef.cryptotrackerapp.domain.repositories

import com.natiqhaciyef.cryptotrackerapp.common.Resource
import com.natiqhaciyef.cryptotrackerapp.data.local.source.PriceHistoryDataSource
import com.natiqhaciyef.cryptotrackerapp.data.models.PriceModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PriceHistoryRepository(private val ds: PriceHistoryDataSource) : PriceHistoryInterface {

    override suspend fun getAllPreviousHistories(): Resource<List<PriceModel>?> =
        if (ds.getAllPreviousHistories() != null && ds.getAllPreviousHistories()!!.isNotEmpty()) {
            Resource.success(ds.getAllPreviousHistories())
        } else {
            Resource.error("Something went wrong", null)
        }


    override suspend fun getAllPreviousHistoriesByCurrencyId(currencyId: String): Resource<List<PriceModel>?> =
        if (ds.getAllPreviousHistories() != null && ds.getAllPreviousHistories()!!.isNotEmpty()) {
            Resource.success(ds.getAllPreviousHistoriesByCurrencyId(currencyId))
        } else {
            Resource.error("Something went wrong", null)
        }

    override suspend fun insertPreviousHistory(previousHistoryModel: PriceModel) =
        ds.insertPreviousHistory(previousHistoryModel)


    override suspend fun deletePreviousHistory(previousHistoryModel: PriceModel) =
        ds.deletePreviousHistory(previousHistoryModel)

}