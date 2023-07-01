package com.natiqhaciyef.cryptotrackerapp.di

import android.content.Context
import androidx.room.Room
import com.natiqhaciyef.cryptotrackerapp.data.local.database.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppTestModule {

    @Provides
    @Named("test_database")
    fun provideInMemoryDatabase(@ApplicationContext context: Context) = Room
        .databaseBuilder(context, AppDatabase::class.java, "database_in_memory")
        .allowMainThreadQueries()
        .build()

}