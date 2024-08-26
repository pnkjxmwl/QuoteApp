package com.example.quotesapp.data.dto


import com.example.quotesapp.domain.QuoteItem
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class QuoteItemDto(
    @Json(name = "quote")
    val quote: String,
    @Json(name = "author")
    val author: String,
    @Json(name = "category")
    val category: String
)

fun QuoteItemDto.toQuoteItem():QuoteItem {
    return QuoteItem(
        quote=quote,
        author=author,
        category=category,
        isSaved = false
    )
}
