package com.example.stocklistingapp.data.remote

import com.example.stocklistingapp.data.remote.dto.CompanyInfoDto
import com.example.stocklistingapp.data.remote.dto.IntradayInfoDto
import com.example.stocklistingapp.domain.model.NotificationModel
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface StockApi {
    @GET("query?function=LISTING_STATUS")
    suspend fun getListings(
        @Query("apikey") apiKey : String = API_KEY
    ): ResponseBody

    @GET("query?function=TIME_SERIES_INTRADAY&interval=60min&datatype=csv")
    suspend fun getIntradayInfo(
        @Query("symbol") symbol : String,
        @Query("apikey") apiKey : String = API_KEY,

    ): ResponseBody

    @GET("query?function=OVERVIEW")
    suspend fun getCompanyInfo(
        @Query("symbol") symbol : String,
        @Query("apikey") apiKey : String = API_KEY,

    ): CompanyInfoDto


    companion object {
        const val  API_KEY = "YLZQ7972W81OZ1FI"
        const val  BASE_URL = "https://alphavantage.co"
    }
}