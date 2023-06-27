package com.natiqhaciyef.cryptotrackerapp.data.local.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.natiqhaciyef.cryptotrackerapp.data.models.PriceModel

@Dao
interface PreviousHistoryDao {

    @Query("SELECT * FROM price_table")
    suspend fun getAllPreviousHistories(): List<PriceModel>?

    @Query("SELECT * FROM price_table WHERE currencyId = :currencyId")
    suspend fun getAllPreviousHistoriesByCurrencyId(currencyId: String): List<PriceModel>?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPreviousHistory(previousHistoryModel: PriceModel)

    @Delete
    suspend fun deletePreviousHistory(previousHistoryModel: PriceModel)

}