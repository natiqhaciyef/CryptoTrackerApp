package com.natiqhaciyef.cryptotrackerapp.domain.usecases.get

import com.natiqhaciyef.cryptotrackerapp.common.Resource
import com.natiqhaciyef.cryptotrackerapp.data.models.CurrencyHistoryModel
import com.natiqhaciyef.cryptotrackerapp.domain.repositories.NetworkRepository
import javax.inject.Inject

class GetCryptoCurrencyHistoryUseCase @Inject constructor(
    private val networkRepository: NetworkRepository
) {

    suspend operator fun invoke(name: String, date: String) : Resource<CurrencyHistoryModel>{
        val data = networkRepository.getCryptoCurrencyHistory(id = name, date = date)

        return if (data == null){
            Resource.error("Something went wrong", null)
        }else {
            Resource.success(data)
        }
    }

}