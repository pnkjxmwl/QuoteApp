package com.example.quotesapp.domain.repository

import com.example.quotesapp.domain.model.Quote
import kotlinx.coroutines.flow.Flow

interface LocalDataRepository {

    suspend fun insertQuote(quote: Quote)
    suspend  fun deleteQuote(quote: Quote)

    fun getAllQuote():Flow<List<Quote>>
}