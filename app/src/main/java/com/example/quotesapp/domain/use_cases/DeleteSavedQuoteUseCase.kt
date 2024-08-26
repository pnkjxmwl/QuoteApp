package com.example.quotesapp.domain.use_cases

import com.example.quotesapp.domain.model.Quote
import com.example.quotesapp.domain.repository.LocalDataRepository
import javax.inject.Inject

data class DeleteSavedQuoteUseCase @Inject constructor(
    private val repository: LocalDataRepository
){
    suspend operator fun invoke(quote: Quote)=
        repository.deleteQuote(quote)
}
