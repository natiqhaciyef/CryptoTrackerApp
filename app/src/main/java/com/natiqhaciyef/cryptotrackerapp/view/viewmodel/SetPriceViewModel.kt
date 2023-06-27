package com.natiqhaciyef.cryptotrackerapp.view.viewmodel

import androidx.lifecycle.viewModelScope
import com.natiqhaciyef.cryptotrackerapp.data.models.PriceModel
import com.natiqhaciyef.cryptotrackerapp.domain.usecases.add.InsertPriceUseCase
import com.natiqhaciyef.cryptotrackerapp.domain.usecases.remove.DeletePriceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SetPriceViewModel @Inject constructor(
    private val insertPriceUseCase: InsertPriceUseCase,
    private val deletePriceUseCase: DeletePriceUseCase,
): BaseViewModel() {

    fun insertPrice(priceModel: PriceModel){
        viewModelScope.launch {
            insertPriceUseCase.invoke(priceModel)
        }
    }

    fun deletePrice(priceModel: PriceModel){
        viewModelScope.launch {
            deletePriceUseCase.invoke(priceModel)
        }
    }
}