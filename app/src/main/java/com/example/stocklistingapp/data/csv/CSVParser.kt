package com.example.stocklistingapp.data.csv

import java.io.InputStream
import java.util.stream.BaseStream

interface CSVParser<T> {
    suspend fun parse(stream: InputStream):List<T>
}