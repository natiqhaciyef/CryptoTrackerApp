package com.natiqhaciyef.cryptotrackerapp.view.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.natiqhaciyef.cryptotrackerapp.data.models.CurrencyModel
import com.natiqhaciyef.cryptotrackerapp.domain.usecases.get.GetAllCryptoCurrenciesUseCase
import com.natiqhaciyef.cryptotrackerapp.common.Status
import com.natiqhaciyef.cryptotrackerapp.data.models.CurrencyHistoryModel
import com.natiqhaciyef.cryptotrackerapp.domain.usecases.get.GetCryptoCurrencyHistoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllCryptoCurrencies: GetAllCryptoCurrenciesUseCase,
    private val getCryptoCurrencyHistoryUseCase: GetCryptoCurrencyHistoryUseCase
) : BaseViewModel() {
    private val _cryptoLiveData = MutableLiveData<List<CurrencyModel>>()
    val cryptoLiveData get() = _cryptoLiveData
    private val _historyLiveData = MutableLiveData<CurrencyHistoryModel>()
    val historyLiveData get() = _historyLiveData

    val isLoadingCurrency = MutableLiveData<Boolean>()
    val isLoadingHistory = MutableLiveData<Boolean>()

    var errorMessageCurrency = MutableLiveData<String>()
    var errorMessageHistory = MutableLiveData<String>()


    init {
        getAllData()
    }

    private fun getAllData(){
        viewModelScope.launch {
            isLoadingCurrency.value = true
            val result = getAllCryptoCurrencies.invoke()
            when (result.status){
                Status.SUCCESS -> {
                    _cryptoLiveData.value = result.data ?: mutableListOf()
                    isLoadingCurrency.value = false
                }
                Status.LOADING -> {
                    isLoadingCurrency.value = true
                }
                Status.ERROR -> {
                    errorMessageCurrency.value = result.message ?: "Error cause not found"
                    isLoadingCurrency.value = false
                }
            }
        }
    }


    fun getHistory(id: String, date: String){
        viewModelScope.launch {
            val result = getCryptoCurrencyHistoryUseCase.invoke(id, date)
            when (result.status){
                Status.SUCCESS -> {
                    _historyLiveData.value = result.data!!
                    isLoadingHistory.value = false
                }
                Status.LOADING -> {
                    isLoadingHistory.value = true
                }
                Status.ERROR -> {
                    errorMessageHistory.value = result.message!!
                    isLoadingHistory.value = true
                }
            }
        }
    }
}