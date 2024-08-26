package com.example.quotesapp.domain.repository

import com.example.quotesapp.data.dto.QuoteItemDto

interface RemoteDataRepository {

    suspend fun getQuote(category:String):QuoteItemDto

}