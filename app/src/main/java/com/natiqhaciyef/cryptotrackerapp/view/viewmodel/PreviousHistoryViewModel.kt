package com.natiqhaciyef.cryptotrackerapp.view.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.natiqhaciyef.cryptotrackerapp.common.Status
import com.natiqhaciyef.cryptotrackerapp.data.models.PriceModel
import com.natiqhaciyef.cryptotrackerapp.domain.usecases.get.GetAllPreviousHistoriesFilteredByCurrencyIdUseCase
import com.natiqhaciyef.cryptotrackerapp.domain.usecases.get.GetAllPreviousHistoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PreviousHistoryViewModel @Inject constructor(
    private val getAllPreviousHistories: GetAllPreviousHistoriesUseCase,
    private val getAllPreviousHistoriesFilteredByCurrencyIdUseCase: GetAllPreviousHistoriesFilteredByCurrencyIdUseCase
) : BaseViewModel() {
    private val _previousHistoriesLiveData = MutableLiveData<List<PriceModel>>()
    val previousHistoriesLiveData get() = _previousHistoriesLiveData

    private val _previousHistoriesFilteredLiveData = MutableLiveData<List<PriceModel>>()
    val previousHistoriesFilteredLiveData get() = _previousHistoriesFilteredLiveData

    val isLoadingPreviousHistories = MutableLiveData<Boolean>(false)
    val errorMessagePreviousHistories = MutableLiveData<String>()

    init {
        getAllPreviousHistories()
    }

    private fun getAllPreviousHistories() {
        isLoadingPreviousHistories.value = true
        viewModelScope.launch {
            val result = getAllPreviousHistories.invoke()

            when (result.status) {
                Status.SUCCESS -> {
                    if (result.data != null) {
                        _previousHistoriesLiveData.value = result.data!!
                        isLoadingPreviousHistories.value = result.data.isEmpty()
                    }
                }

                Status.ERROR -> {
                    errorMessagePreviousHistories.value = result.message!!
                    isLoadingPreviousHistories.value = false
                }

                Status.LOADING -> {
                    isLoadingPreviousHistories.value = true
                }
            }
        }
    }


    fun getAllPreviousHistoriesFilteredByCurrencyId(currencyId: String) {
        isLoadingPreviousHistories.value = true
        viewModelScope.launch {
            val result = getAllPreviousHistoriesFilteredByCurrencyIdUseCase.invoke(currencyId)

            when (result.status) {
                Status.SUCCESS -> {
                    if (result.data != null) {
                        _previousHistoriesFilteredLiveData.value = result.data!!
                        isLoadingPreviousHistories.value = result.data.isEmpty()
                    }
                }

                Status.ERROR -> {
                    errorMessagePreviousHistories.value = result.message!!
                    isLoadingPreviousHistories.value = false
                    println(result.message)
                }

                Status.LOADING -> {
                    isLoadingPreviousHistories.value = true
                }
            }
        }
    }
}