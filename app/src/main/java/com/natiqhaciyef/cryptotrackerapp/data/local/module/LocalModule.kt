package com.natiqhaciyef.cryptotrackerapp.data.local.module

import android.content.Context
import androidx.room.Room
import com.natiqhaciyef.cryptotrackerapp.data.local.database.dao.PreviousHistoryDao
import com.natiqhaciyef.cryptotrackerapp.data.local.database.database.AppDatabase
import com.natiqhaciyef.cryptotrackerapp.data.local.source.PriceHistoryDataSource
import com.natiqhaciyef.cryptotrackerapp.domain.repositories.PriceHistoryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) = Room
        .databaseBuilder(context = context, klass = AppDatabase::class.java, "app_database")
        .build()

    @Provides
    @Singleton
    fun providePreviousHistoryDao(db: AppDatabase) = db.getPreviousHistoryDao()

    @Provides
    @Singleton
    fun providePreviousHistoryDataSource(dao: PreviousHistoryDao) = PriceHistoryDataSource(dao)

    @Provides
    @Singleton
    fun providePreviousHistoryRepository(ds: PriceHistoryDataSource) = PriceHistoryRepository(ds)

}