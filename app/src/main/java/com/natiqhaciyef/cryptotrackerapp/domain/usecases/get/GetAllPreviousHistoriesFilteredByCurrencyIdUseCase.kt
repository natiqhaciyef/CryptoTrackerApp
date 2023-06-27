package com.natiqhaciyef.cryptotrackerapp.domain.usecases.get

import com.natiqhaciyef.cryptotrackerapp.common.Resource
import com.natiqhaciyef.cryptotrackerapp.data.models.PriceModel
import com.natiqhaciyef.cryptotrackerapp.domain.repositories.PriceHistoryRepository
import javax.inject.Inject

class GetAllPreviousHistoriesFilteredByCurrencyIdUseCase @Inject constructor(
    private val priceRepository: PriceHistoryRepository,
){

    suspend operator fun invoke(currencyId: String): Resource<List<PriceModel>> {
        val data = priceRepository.getAllPreviousHistoriesByCurrencyId(currencyId)

        return if (data != null) {
            Resource.success(data)
        } else {
            Resource.error("Something went wrong", null)
        }
    }

}