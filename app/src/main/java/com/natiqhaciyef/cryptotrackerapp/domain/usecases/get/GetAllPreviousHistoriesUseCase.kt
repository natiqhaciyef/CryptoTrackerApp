package com.natiqhaciyef.cryptotrackerapp.domain.usecases.get

import com.natiqhaciyef.cryptotrackerapp.common.Resource
import com.natiqhaciyef.cryptotrackerapp.data.models.PriceModel
import com.natiqhaciyef.cryptotrackerapp.domain.repositories.PriceHistoryRepository
import javax.inject.Inject

class GetAllPreviousHistoriesUseCase @Inject constructor(
    private val priceRepository: PriceHistoryRepository
){

    suspend operator fun invoke(): Resource<List<PriceModel>>{
        val data = priceRepository.getAllPreviousHistories()

        return if (data != null) {
            Resource.success(data)

        } else {
            Resource.error("Something went wrong", null)
        }
    }

}