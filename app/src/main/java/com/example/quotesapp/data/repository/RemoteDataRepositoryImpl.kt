package com.example.quotesapp.data.repository

import android.util.Log
import com.example.quotesapp.data.QuotesApi
import com.example.quotesapp.data.dto.QuoteItemDto
import com.example.quotesapp.domain.repository.RemoteDataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RemoteDataRepositoryImpl @Inject constructor(
   private val api:QuotesApi
) :RemoteDataRepository {

    override suspend fun getQuote(category: String): QuoteItemDto {
        return withContext(Dispatchers.Default){
            val quotesItemDto = api.getQuote(category = category)
            val firstItemDto = quotesItemDto.first()
            return@withContext firstItemDto
        }
    }
}