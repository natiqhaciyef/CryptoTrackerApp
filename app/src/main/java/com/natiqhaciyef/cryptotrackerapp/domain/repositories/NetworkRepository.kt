package com.natiqhaciyef.cryptotrackerapp.domain.repositories

import androidx.lifecycle.MutableLiveData
import com.natiqhaciyef.cryptotrackerapp.common.Resource
import com.natiqhaciyef.cryptotrackerapp.data.models.CurrenciesList
import com.natiqhaciyef.cryptotrackerapp.data.models.ExtendedCurrencyModel
import com.natiqhaciyef.cryptotrackerapp.data.network.source.NetworkDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NetworkRepository(private val nds: NetworkDataSource) {
    suspend fun getAllCryptos(): MutableLiveData<Resource<List<ExtendedCurrencyModel>?>> {
        val data = nds.getAllCryptos()
        val list = if (data != null){
            listOf(
                ExtendedCurrencyModel(
                    "Bitcoin",
                    "https://assets.coingecko.com/coins/images/1/large/bitcoin.png?1547033579",
                    data.bitcoin
                ),
                ExtendedCurrencyModel(
                    "Ethereum",
                    "https://assets.coingecko.com/coins/images/279/large/ethereum.png?1595348880",
                    data.ethereum
                ),

                ExtendedCurrencyModel(
                    "Ripple",
                    "https://assets.coingecko.com/coins/images/44/large/xrp-symbol-white-128.png?1605778731",
                    data.ripple
                )
            )
        }else{
            mutableListOf()
        }

        return MutableLiveData(Resource.success(list))

    }

}