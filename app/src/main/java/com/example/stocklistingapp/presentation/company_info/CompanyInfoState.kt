package com.example.stocklistingapp.presentation.company_info

import com.example.stocklistingapp.domain.model.CompanyInfo
import com.example.stocklistingapp.domain.model.IntradayInfo

data class CompanyInfoState (
    val stockInfos : List<IntradayInfo> = emptyList(),
    val company : CompanyInfo? = null,
    val isLoading : Boolean = false,
    val error : String? = null
)