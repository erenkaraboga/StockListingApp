package com.example.stocklistingapp.data.mapper

import com.example.stocklistingapp.data.local.CompanyListingEntity
import com.example.stocklistingapp.data.remote.dto.CompanyInfoDto
import com.example.stocklistingapp.domain.model.CompanyInfo
import com.example.stocklistingapp.domain.model.CompanyListing

fun CompanyListingEntity.toCompanyListing(): CompanyListing {
    return CompanyListing(
        name = name,
        symbol = symbol,
        exchange = exchange
    )
}

fun CompanyListing.toCompanyListingEntity(): CompanyListingEntity {
    return CompanyListingEntity(
        name = name,
        symbol = symbol,
        exchange = exchange
    )
}

fun CompanyInfoDto.toCompanyInfo(): CompanyInfo {
    return CompanyInfo(
        symbol = symbol ?: "",
        description = description ?: "",
        name = name ?: "",
        country = country ?: "",
        industry = industry ?: ""
    )
}