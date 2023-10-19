package com.example.stocklistingapp.domain.repository

import android.app.DownloadManager.Query
import com.example.stocklistingapp.domain.model.CompanyListing
import com.example.stocklistingapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface StockRepository {

    suspend fun getCompanyListings(
        fetchFromRemote : Boolean,
        query: String
    ): Flow<Resource<List<CompanyListing>>>
}