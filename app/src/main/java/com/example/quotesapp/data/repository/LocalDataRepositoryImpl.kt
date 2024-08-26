package com.example.quotesapp.data.repository

import com.example.quotesapp.data.QuoteDao
import com.example.quotesapp.domain.model.Quote
import com.example.quotesapp.domain.repository.LocalDataRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataRepositoryImpl @Inject constructor(
    private val dao:QuoteDao
) :LocalDataRepository {
    override suspend fun insertQuote(quote: Quote) {
        dao.insertQuote(quote)
    }

    override suspend fun deleteQuote(quote: Quote) {
        dao.deleteQuote(quote)
    }

    override fun getAllQuote(): Flow<List<Quote>> {
        return  dao.getAllQuotes()
    }
}