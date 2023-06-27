package com.natiqhaciyef.cryptotrackerapp.data.local.database.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.natiqhaciyef.cryptotrackerapp.data.local.database.dao.PreviousHistoryDao
import com.natiqhaciyef.cryptotrackerapp.data.models.PriceModel

@Database(entities = [PriceModel::class], version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun getPreviousHistoryDao(): PreviousHistoryDao
}