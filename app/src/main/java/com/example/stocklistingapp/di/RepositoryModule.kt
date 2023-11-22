package com.example.stocklistingapp.di

import com.example.stocklistingapp.data.csv.CSVParser
import com.example.stocklistingapp.data.csv.CompanyListingsParser
import com.example.stocklistingapp.data.repository.StockRepositoryImpl
import com.example.stocklistingapp.domain.model.CompanyListing
import com.example.stocklistingapp.domain.repository.StockRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindCompanyListingsParser(
        companyListingsParser: CompanyListingsParser,
    ): CSVParser<CompanyListing>

    @Binds
    @Singleton
    abstract fun bindStockRepository(
        stockRepositoryImpl: StockRepositoryImpl,
    ): StockRepository
}