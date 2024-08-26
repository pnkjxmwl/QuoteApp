package com.example.quotesapp.domain

data class QuoteItem(
    val quote: String,
    val author: String,
    val category: String,
    val isSaved:Boolean=false
)
