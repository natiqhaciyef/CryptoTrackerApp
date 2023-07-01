package com.natiqhaciyef.cryptotrackerapp.view.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.natiqhaciyef.cryptotrackerapp.common.Resource
import com.natiqhaciyef.cryptotrackerapp.data.models.PriceModel
import com.natiqhaciyef.cryptotrackerapp.domain.repositories.PriceHistoryInterface
import com.natiqhaciyef.cryptotrackerapp.domain.repositories.PriceHistoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SetPriceViewModel @Inject constructor(
    private val repo: PriceHistoryInterface
) : BaseViewModel() {

    val insertPriceErrorMessage = MutableLiveData<Resource<PriceModel>>()
    val deletePriceErrorMessage = MutableLiveData<Resource<PriceModel>>()

    fun insertPrice(priceModel: PriceModel) {
        viewModelScope.launch {
            if (priceModel.currencyId.isNotEmpty() && priceModel.maxPrice > priceModel.minPrice && priceModel.currencyName.isNotEmpty()){
                repo.insertPreviousHistory(priceModel)
                insertPriceErrorMessage.postValue(Resource.success(priceModel))
            }else{
                insertPriceErrorMessage.postValue(Resource.error("Something went wrong", null))
            }
        }
    }

    fun deletePrice(priceModel: PriceModel) {
        viewModelScope.launch {
            if (priceModel.currencyId.isNotEmpty() && priceModel.maxPrice > priceModel.minPrice && priceModel.currencyName.isNotEmpty()){
                repo.deletePreviousHistory(priceModel)
                deletePriceErrorMessage.postValue(Resource.success(priceModel))
            }else{
                deletePriceErrorMessage.postValue(Resource.error("Something went wrong", null))
            }
        }
    }
}