package com.example.stocklistingapp.data.csv

import com.example.stocklistingapp.data.mapper.toIntradayInfo
import com.example.stocklistingapp.data.remote.dto.IntradayInfoDto
import com.example.stocklistingapp.domain.model.CompanyListing
import com.example.stocklistingapp.domain.model.IntradayInfo
import com.opencsv.CSVReader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.io.InputStreamReader
import java.time.LocalDateTime
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class IntradayInfoParser @Inject constructor() : CSVParser<IntradayInfo> {
    override suspend fun parse(stream: InputStream): List<IntradayInfo> {
        val csvReader = CSVReader(InputStreamReader(stream))

        return withContext(Dispatchers.IO) {
            csvReader.readAll().drop(1)
                .mapNotNull { line ->
                    val timestamp = line.getOrNull(0) ?: return@mapNotNull null
                    val close = line.getOrNull(4) ?: return@mapNotNull null
                    val dto = IntradayInfoDto(
                        timestamp, close.toDouble()
                    )
                    dto.toIntradayInfo()
                }.filter {
                    it.date.dayOfMonth == LocalDateTime.now().minusDays(4).dayOfMonth
                }.sortedBy {
                    it.date.hour
                }.also {
                    csvReader.close()
                }
        }
    }
}