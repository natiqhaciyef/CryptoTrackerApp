package com.natiqhaciyef.cryptotrackerapp.view.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.natiqhaciyef.cryptotrackerapp.common.Resource
import com.natiqhaciyef.cryptotrackerapp.common.Status
import com.natiqhaciyef.cryptotrackerapp.data.models.PriceModel
import com.natiqhaciyef.cryptotrackerapp.domain.repositories.PriceHistoryInterface
import com.natiqhaciyef.cryptotrackerapp.domain.repositories.PriceHistoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PriceHistoryViewModel @Inject constructor(
    private val repo: PriceHistoryInterface,
) : BaseViewModel() {
    private val _previousHistoriesLiveData = MutableLiveData<List<PriceModel>>()
    val previousHistoriesLiveData get() = _previousHistoriesLiveData

    private val _previousHistoriesFilteredLiveData = MutableLiveData<List<PriceModel>>()
    val previousHistoriesFilteredLiveData get() = _previousHistoriesFilteredLiveData

    val isLoadingPreviousHistories = MutableLiveData<Boolean>(false)
    val errorMessagePreviousHistories = MutableLiveData<Resource<List<PriceModel>>>()

    init {
        getAllPreviousHistories()
    }

    private fun getAllPreviousHistories() {
        isLoadingPreviousHistories.value = true
        viewModelScope.launch {
            val result = repo.getAllPreviousHistories()

            when (result.status) {
                Status.SUCCESS -> {
                    if (result.data != null) {
                        _previousHistoriesLiveData.value = result.data!!
                        isLoadingPreviousHistories.value = result.data.isEmpty()
                        errorMessagePreviousHistories.value = Resource.success(result.data)
                    }
                }

                Status.ERROR -> {
                    errorMessagePreviousHistories.value = Resource.error(result.message!!, null)
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
            if (currencyId.isNotEmpty()) {
                val result = repo.getAllPreviousHistoriesByCurrencyId(currencyId)

                when (result.status) {
                    Status.SUCCESS -> {
                        if (result.data != null) {
                            _previousHistoriesFilteredLiveData.value = result.data!!
                            isLoadingPreviousHistories.value = result.data.isEmpty()
                            errorMessagePreviousHistories.postValue(Resource.success(result.data))
                        }
                    }

                    Status.ERROR -> {
                        errorMessagePreviousHistories.postValue(Resource.error(result.message!!, null))
                        isLoadingPreviousHistories.value = false
                        println(result.message)
                    }

                    Status.LOADING -> {
                        isLoadingPreviousHistories.value = true
                    }
                }
            }else{
                errorMessagePreviousHistories.postValue(Resource.error("Empty currency id !", null))
            }
        }
    }
}