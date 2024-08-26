package com.example.quotesapp.domain.use_cases

import com.example.quotesapp.domain.model.Quote
import com.example.quotesapp.domain.repository.LocalDataRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

data class GetSavedQuoteUseCase@Inject constructor(
    private val repository: LocalDataRepository
){
     operator fun invoke():Flow<List<Quote>>{
         return repository.getAllQuote()
     }
}
