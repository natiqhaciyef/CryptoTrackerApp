package com.natiqhaciyef.cryptotrackerapp.data.network.source

import com.natiqhaciyef.cryptotrackerapp.data.network.service.NetworkService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NetworkDataSource(private val ns: NetworkService) {

    suspend fun getAllCryptos() = withContext(Dispatchers.IO){
        ns.getCryptoCurrencyById()
    }

}