package com.example.quotesapp.data

import com.example.quotesapp.data.dto.QuoteItemDto
import com.example.quotesapp.util.Constant
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface QuotesApi {

    @GET("/v1/quotes")
    suspend fun getQuote(
        @Header("X-Api-Key") apiKey:String= Constant.API_KEY,
        @Query("category") category:String
    ):List<QuoteItemDto>
}