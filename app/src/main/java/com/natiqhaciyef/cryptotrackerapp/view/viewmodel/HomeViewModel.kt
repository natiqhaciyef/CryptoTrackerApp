package com.natiqhaciyef.cryptotrackerapp.view.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.natiqhaciyef.cryptotrackerapp.common.Status
import com.natiqhaciyef.cryptotrackerapp.data.models.CurrenciesList
import com.natiqhaciyef.cryptotrackerapp.data.models.CurrencyModel
import com.natiqhaciyef.cryptotrackerapp.data.models.ExtendedCurrencyModel
import com.natiqhaciyef.cryptotrackerapp.domain.repositories.NetworkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repo: NetworkRepository
) : BaseViewModel() {
    private var _cryptoLiveData = MutableLiveData<List<ExtendedCurrencyModel>>()
    val cryptoLiveData get() = _cryptoLiveData


    init {
        getAll()
    }

    private fun getAll() {
        viewModelScope.launch {
            val live = repo.getAllCryptos()
            if (live.value?.status == Status.SUCCESS) {
                _cryptoLiveData.value = live.value!!.data!!
            }
        }
    }
}