package com.natiqhaciyef.cryptotrackerapp.domain.usecases.get

import com.natiqhaciyef.cryptotrackerapp.data.models.CurrencyModel
import com.natiqhaciyef.cryptotrackerapp.domain.repositories.NetworkRepository
import com.natiqhaciyef.cryptotrackerapp.common.Resource
import javax.inject.Inject

class GetAllCryptoCurrenciesUseCase @Inject constructor(
    private val networkRepository: NetworkRepository
) {
    suspend operator fun invoke(): Resource<List<CurrencyModel>?> {
        val data = networkRepository.getAllCryptoCurrencies()

        return if (data == null) {
            Resource.error("Something went wrong", null)
        } else if (data.isNotEmpty()) {
            Resource.success(data)
        } else {
            Resource.loading(data)
        }
    }
}