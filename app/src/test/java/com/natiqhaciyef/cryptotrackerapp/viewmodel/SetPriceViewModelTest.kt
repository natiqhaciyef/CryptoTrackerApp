package com.natiqhaciyef.cryptotrackerapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.natiqhaciyef.artapptesting.util.MainCoroutineRule
import com.natiqhaciyef.artapptesting.util.getOrAwaitValue
import com.natiqhaciyef.cryptotrackerapp.common.Status
import com.natiqhaciyef.cryptotrackerapp.data.models.PriceModel
import com.natiqhaciyef.cryptotrackerapp.repo.FakePriceHistoryRepository
import com.natiqhaciyef.cryptotrackerapp.view.viewmodel.SetPriceViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
class SetPriceViewModelTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: SetPriceViewModel

    @Before
    fun setup(){
        viewModel = SetPriceViewModel(FakePriceHistoryRepository)
    }

    @Test
    fun `insert data to room database returns success`(){
        viewModel.insertPrice(PriceModel(id = 0, minPrice = 20000.0,21000.0, currencyId = "bitcoin", currencyName = "Bitcoin", currencyImage = "empty"))
        val data = viewModel.insertPriceErrorMessage.getOrAwaitValue()
        assertThat(data.status).isEqualTo(Status.SUCCESS)
    }


    @Test
    fun `insert data to room database with minPrice greater than maxPrice returns error`(){
        viewModel.insertPrice(PriceModel(id = 0, minPrice = 21000.0,20000.0, currencyId = "bitcoin", currencyName = "Bitcoin", currencyImage = "empty"))
        val data = viewModel.insertPriceErrorMessage.getOrAwaitValue()
        assertThat(data.status).isEqualTo(Status.ERROR)
    }


    @Test
    fun `insert data to room database without currencyId returns error`(){
        viewModel.insertPrice(PriceModel(id = 0, minPrice = 20000.0,21000.0, currencyId = "", currencyName = "Bitcoin", currencyImage = "empty"))
        val data = viewModel.insertPriceErrorMessage.getOrAwaitValue()
        assertThat(data.status).isEqualTo(Status.ERROR)
    }



    @Test
    fun `delete data to room database returns success`(){
        viewModel.deletePrice(PriceModel(id = 0, minPrice = 20000.0,21000.0, currencyId = "bitcoin", currencyName = "Bitcoin", currencyImage = "empty"))
        val data = viewModel.deletePriceErrorMessage.getOrAwaitValue()
        assertThat(data.status).isEqualTo(Status.SUCCESS)
    }


    @Test
    fun `delete data to room database with minPrice greater than maxPrice returns error`(){
        viewModel.deletePrice(PriceModel(id = 0, minPrice = 21000.0,20000.0, currencyId = "bitcoin", currencyName = "Bitcoin", currencyImage = "empty"))
        val data = viewModel.deletePriceErrorMessage.getOrAwaitValue()
        assertThat(data.status).isEqualTo(Status.ERROR)
    }


    @Test
    fun `delete data to room database without currencyId returns error`(){
        viewModel.deletePrice(PriceModel(id = 0, minPrice = 20000.0,21000.0, currencyId = "", currencyName = "Bitcoin", currencyImage = "empty"))
        val data = viewModel.deletePriceErrorMessage.getOrAwaitValue()
        assertThat(data.status).isEqualTo(Status.ERROR)
    }

}