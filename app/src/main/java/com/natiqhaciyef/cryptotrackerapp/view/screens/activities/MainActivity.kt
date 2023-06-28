package com.natiqhaciyef.cryptotrackerapp.view.screens.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.natiqhaciyef.cryptotrackerapp.data.models.ExtendedCurrencyModel
import com.natiqhaciyef.cryptotrackerapp.data.models.PriceModel
import com.natiqhaciyef.cryptotrackerapp.data.service.NotificationService
import com.natiqhaciyef.cryptotrackerapp.data.worker.NotificationSender
import com.natiqhaciyef.cryptotrackerapp.databinding.ActivityMainBinding
import com.natiqhaciyef.cryptotrackerapp.domain.work.NotificationWork
import com.natiqhaciyef.cryptotrackerapp.view.viewmodel.HomeViewModel
import com.natiqhaciyef.cryptotrackerapp.view.viewmodel.PreviousHistoryViewModel
import com.natiqhaciyef.cryptotrackerapp.view.viewmodel.SetPriceViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var previousViewModel: PreviousHistoryViewModel
    private lateinit var setPricesViewModel: SetPriceViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        previousViewModel = ViewModelProvider(this)[PreviousHistoryViewModel::class.java]
        setPricesViewModel = ViewModelProvider(this)[SetPriceViewModel::class.java]

        Intent(this, NotificationService::class.java).also {
            startService(it)
            observeLiveData()
        }
    }

    private fun observeLiveData(){
        homeViewModel.cryptoLiveData.observe(this) { currencies ->
            previousViewModel.previousHistoriesLiveData.observe(this) { histories ->
                val pricesHistoryName = histories.map { it.currencyName }

                if (pricesHistoryName.contains("Bitcoin")) {
                    priceNotificationSender("Bitcoin", histories, currencies)
                }

                if (pricesHistoryName.contains("Ethereum")) {
                    priceNotificationSender("Ethereum", histories, currencies)
                }

                if (pricesHistoryName.contains("Ripple")) {
                    priceNotificationSender("Ripple", histories, currencies)
                }
            }
        }
    }
    private fun priceNotificationSender(title: String, histories: List<PriceModel>, currencies: List<ExtendedCurrencyModel>){
        val maxPrice =
            histories.filter { it.currencyName == title && it.currencyId == title.lowercase() }
                .maxOf { it.maxPrice }

        val minPrice =
            histories.filter { it.currencyName == title && it.currencyId == title.lowercase() }
                .minOf { it.minPrice }

        val currency = currencies.filter { it.title == title }[0]
        if (maxPrice < currency.currencyModel.usd){
            NotificationWork.activateWorkManager(
                this,
                "Max price alert",
                "$title's max price rose from your limit. Please check app and change limitation."
            )
            setPricesViewModel.deletePrice(histories.filter { it.maxPrice == maxPrice }[0])
        } else if (minPrice > currency.currencyModel.usd){
            NotificationWork.activateWorkManager(
                this,
                "Min price alert",
                "$title's min price down from your limit. Please check app and change limitation."
            )
            setPricesViewModel.deletePrice(histories.filter { it.minPrice == minPrice }[0])
        }

    }
}