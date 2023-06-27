package com.natiqhaciyef.cryptotrackerapp.domain.usecases.add

import com.natiqhaciyef.cryptotrackerapp.data.models.PriceModel
import com.natiqhaciyef.cryptotrackerapp.domain.repositories.PriceHistoryRepository
import javax.inject.Inject

class InsertPriceUseCase @Inject constructor(
    private val priceHistory: PriceHistoryRepository
) {

    suspend operator fun invoke(previousHistoryModel: PriceModel){
        priceHistory.insertPreviousHistory(previousHistoryModel)
    }
}