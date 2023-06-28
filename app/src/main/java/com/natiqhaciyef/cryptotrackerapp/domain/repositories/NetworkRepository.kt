package com.natiqhaciyef.cryptotrackerapp.domain.repositories

import com.natiqhaciyef.cryptotrackerapp.data.network.source.NetworkDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NetworkRepository(private val nds: NetworkDataSource) {
    suspend fun getAllCryptos() = nds.getAllCryptos()
}