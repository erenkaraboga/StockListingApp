package com.example.stocklistingapp.data.repository

import com.example.stocklistingapp.data.csv.CSVParser
import com.example.stocklistingapp.data.csv.CompanyListingsParser
import com.example.stocklistingapp.data.csv.IntradayInfoParser
import com.example.stocklistingapp.data.local.StockDatabase
import com.example.stocklistingapp.data.mapper.toCompanyInfo
import com.example.stocklistingapp.data.mapper.toCompanyListing
import com.example.stocklistingapp.data.mapper.toCompanyListingEntity
import com.example.stocklistingapp.data.remote.StockApi
import com.example.stocklistingapp.domain.model.CompanyInfo
import com.example.stocklistingapp.domain.model.CompanyListing
import com.example.stocklistingapp.domain.model.IntradayInfo
import com.example.stocklistingapp.domain.repository.StockRepository
import com.example.stocklistingapp.util.Resource
import com.opencsv.CSVReader
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.io.InputStreamReader
import java.lang.reflect.Constructor
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class StockRepositoryImpl @Inject constructor(
    val api: StockApi,
    val db: StockDatabase,
    val companyListingsParser: CSVParser<CompanyListing>,
    val intradayInfoParser: CSVParser<IntradayInfo>,
) : StockRepository {
    private val dao = db.dao
    override suspend fun getCompanyListings(
        fetchFromRemote: Boolean,
        query: String,
    ): Flow<Resource<List<CompanyListing>>> {
        return flow {
            emit(Resource.Loading(true))
            val localListings = dao.searchCompanyListing(query)
            emit(Resource.Success(data = localListings.map { it.toCompanyListing() }))
            val isDbEmpty = localListings.isEmpty() && query.isBlank()
            val shouldJustLoadFromCache = !isDbEmpty && !fetchFromRemote
            if (shouldJustLoadFromCache) {
                emit(Resource.Loading(false))
                return@flow
            }
            val remoteListings = try {
                val response = api.getListings()
                companyListingsParser.parse(response.byteStream())
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Could not load data"))
                null

            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("Could not load data"))
                null
            }
            remoteListings?.let { listings ->
                dao.clearCompanyListings()
                dao.insertCompanyListings(
                    listings.map { it.toCompanyListingEntity() })
                emit(
                    Resource.Success(
                        data = dao.searchCompanyListing("").map { it.toCompanyListing() })
                )
                emit(Resource.Loading(isLoading = false))
            }

        }
    }

    override suspend fun getIntradayInfo(symbol: String): Resource<List<IntradayInfo>> {
        return try {
            val response = api.getIntradayInfo(symbol)
            val results = intradayInfoParser.parse(response.byteStream())
            Resource.Success(results)
        } catch (e: IOException) {
            e.printStackTrace()
            Resource.Error("Could not load intraday info")
        } catch (e: HttpException) {
            e.printStackTrace()
            Resource.Error("Could not load intraday info")
        }
    }

    override suspend fun getCompanyInfo(symbol: String): Resource<CompanyInfo> {
        return try {
            val result = api.getCompanyInfo(symbol)
            Resource.Success(result.toCompanyInfo())
        } catch (e: IOException) {
            e.printStackTrace()
            Resource.Error("Could not load company info")
        } catch (e: HttpException) {
            e.printStackTrace()
            Resource.Error("Could not load company info")
        }
    }
}