package com.example.quotesapp.domain.use_cases

data class UseCases(
    val getQuoteUseCase: GetQuoteUseCase,
    val deleteSavedQuoteUseCase: DeleteSavedQuoteUseCase,
    val insertSavedQuoteUseCase: InsertSavedQuoteUseCase,
    val getSavedQuoteUseCase: GetSavedQuoteUseCase
)
