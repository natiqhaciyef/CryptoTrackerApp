package com.natiqhaciyef.cryptotrackerapp.domain.usecases.remove

import com.natiqhaciyef.cryptotrackerapp.data.models.PriceModel
import com.natiqhaciyef.cryptotrackerapp.domain.repositories.PriceHistoryRepository
import javax.inject.Inject

class DeletePriceUseCase @Inject constructor(
    private val previousHistoryRepository: PriceHistoryRepository
) {

    suspend operator fun invoke(previousHistoryModel: PriceModel){
        previousHistoryRepository.deletePreviousHistory(previousHistoryModel)
    }
}