package com.example.stocklistingapp.di

import android.app.Application
import androidx.room.Room
import com.example.stocklistingapp.data.local.StockDatabase
import com.example.stocklistingapp.data.remote.StockApi
import com.example.stocklistingapp.util.firebase_analytics.AnalyticsHelper
import com.example.stocklistingapp.util.firebase_analytics.AnalyticsLogger
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideStockApi():StockApi{
        return Retrofit.Builder()
            .baseUrl(StockApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()

    }
    @Provides
    @Singleton
    fun provideStockDatabase(app: Application) : StockDatabase{
        return Room.databaseBuilder(app
            ,StockDatabase::class.java
            ,"stockdb.db")
            .build()
    }
    @InstallIn(SingletonComponent::class)
    @Module
    abstract class AnalyticsModule {
        @Binds
        abstract fun bindsAnalyticsHelper(
            analyticsHelperImpl: AnalyticsLogger
        ): AnalyticsHelper
    }
}