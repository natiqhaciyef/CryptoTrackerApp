package com.natiqhaciyef.cryptotrackerapp.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.ar.core.Config
import com.google.common.truth.Truth.assertThat
import com.natiqhaciyef.cryptotrackerapp.data.local.database.dao.PreviousHistoryDao
import com.natiqhaciyef.cryptotrackerapp.data.local.database.database.AppDatabase
import com.natiqhaciyef.cryptotrackerapp.data.models.PriceModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named
import kotlin.math.max

@SmallTest
@ExperimentalCoroutinesApi
@HiltAndroidTest
class PriceDaoTest {
    @get: Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get: Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    @Named("test_database")
    lateinit var database: AppDatabase
    private lateinit var dao: PreviousHistoryDao

    @Before
    fun setup(){
        hiltRule.inject()
        dao = database.getPreviousHistoryDao()
    }

    @After
    fun teardown() {
        database.close()
    }


    @Test
    fun `getAllPreviousHistoriesFunctionReturnsSuccess`() = runBlockingTest {
        val priceModel = PriceModel(
            id = 1201,
            minPrice = 21000.0,
            maxPrice = 23000.0,
            currencyId = "bitcoin",
            currencyImage = "empty",
            currencyName = "Bitcoin"
        )
        dao.insertPreviousHistory(priceModel)
//        delay(1500)
        val list = dao.getAllPreviousHistories()
        assertThat(list).contains(priceModel)
    }


    @Test
    fun `getAllPreviousHistoriesFilteredHistoryFunctionReturnsSuccess`() = runBlockingTest {
        val priceModel = PriceModel(
            id = 1202,
            minPrice = 21000.0,
            maxPrice = 23000.0,
            currencyId = "ethereum",
            currencyImage = "empty",
            currencyName = "Ethereum"
        )
        dao.insertPreviousHistory(priceModel)
//        delay(1500)
        val list = dao.getAllPreviousHistoriesByCurrencyId("ethereum")
        assertThat(list).contains(priceModel)
    }
}