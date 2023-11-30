package com.example.stocklistingapp.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.example.stocklistingapp.common.extensions.Preferences
import com.example.stocklistingapp.data.local.StockDatabase
import com.example.stocklistingapp.data.remote.StockApi
import com.example.stocklistingapp.domain.model.AnalyticsHelper
import com.example.stocklistingapp.common.firebase_analytics.FirebaseAnalyticsService
import com.example.stocklistingapp.data.remote.NotificationApi
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
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
    fun provideNotificationApi():NotificationApi{
        return Retrofit.Builder()
            .baseUrl(NotificationApi.BASE_URL)
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
    @Module
    @InstallIn(SingletonComponent::class)
    abstract class AnalyticsModule {
        @Binds
        abstract fun bindsAnalyticsHelper(
            analyticsHelperImpl: FirebaseAnalyticsService
        ): AnalyticsHelper

        companion object {
            @Provides
            @Singleton
            fun provideFirebaseAnalytics(): FirebaseAnalytics {
                return Firebase.analytics
            }
        }
    }

    @Provides
    @Singleton
    fun provideSharedPreferences(app: Application): SharedPreferences = app.getSharedPreferences(
        Preferences.PREFS, Context.MODE_PRIVATE)
}