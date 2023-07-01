package com.natiqhaciyef.cryptotrackerapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
import com.natiqhaciyef.artapptesting.util.MainCoroutineRule
import com.natiqhaciyef.artapptesting.util.getOrAwaitValue
import com.natiqhaciyef.cryptotrackerapp.common.Status
import com.natiqhaciyef.cryptotrackerapp.data.models.PriceModel
import com.natiqhaciyef.cryptotrackerapp.repo.FakePriceHistoryRepository
import com.natiqhaciyef.cryptotrackerapp.view.viewmodel.PriceHistoryViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class PriceHistoryViewModelTest {
    @get: Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get: Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: PriceHistoryViewModel

    @Before
    fun setup(){
        viewModel = PriceHistoryViewModel(FakePriceHistoryRepository)
    }

    @Test
    fun `get all previous price histories filtered by currency id returns success`(){
        viewModel.getAllPreviousHistoriesFilteredByCurrencyId("bitcoin")
        val data = viewModel.errorMessagePreviousHistories.getOrAwaitValue()
        Truth.assertThat(data.status).isEqualTo(Status.SUCCESS)
    }

    @Test
    fun `get all previous price histories filtered without currency id returns error`(){
        viewModel.getAllPreviousHistoriesFilteredByCurrencyId("")
        val data = viewModel.errorMessagePreviousHistories.getOrAwaitValue()
        Truth.assertThat(data.status).isEqualTo(Status.ERROR)
    }
}