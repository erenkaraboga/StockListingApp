package com.example.stocklistingapp.domain.repository

import com.example.stocklistingapp.domain.model.CompanyInfo
import com.example.stocklistingapp.domain.model.CompanyListing
import com.example.stocklistingapp.domain.model.IntradayInfo
import com.example.stocklistingapp.common.resources.Resource
import kotlinx.coroutines.flow.Flow

interface StockRepository {
    suspend fun getCompanyListings(
        fetchFromRemote: Boolean,
        query: String,
    ): Flow<Resource<List<CompanyListing>>>

    suspend fun getIntradayInfo(
        symbol: String,
    ): Resource<List<IntradayInfo>>

    suspend fun getCompanyInfo(
        symbol: String,
    ): Resource<CompanyInfo>
}