package com.natiqhaciyef.cryptotrackerapp.repo

import com.natiqhaciyef.cryptotrackerapp.common.Resource
import com.natiqhaciyef.cryptotrackerapp.data.models.PriceModel
import com.natiqhaciyef.cryptotrackerapp.domain.repositories.PriceHistoryInterface

object FakePriceHistoryRepository : PriceHistoryInterface {
    //Fake database
    private val list = mutableListOf<PriceModel>()

    override suspend fun getAllPreviousHistories(): Resource<List<PriceModel>?> {
        return Resource.success(list)
    }

    override suspend fun getAllPreviousHistoriesByCurrencyId(currencyId: String):Resource<List<PriceModel>?> {
        return Resource.success(
            list.filter {
                it.currencyId == currencyId
            }
        )
    }

    override suspend fun insertPreviousHistory(previousHistoryModel: PriceModel) {
        list.add(previousHistoryModel)
    }

    override suspend fun deletePreviousHistory(previousHistoryModel: PriceModel) {
        list.remove(previousHistoryModel)
    }
}