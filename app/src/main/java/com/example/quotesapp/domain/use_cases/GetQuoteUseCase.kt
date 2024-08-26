package com.example.quotesapp.domain.use_cases

import com.example.quotesapp.data.dto.QuoteItemDto
import com.example.quotesapp.domain.repository.RemoteDataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetQuoteUseCase @Inject constructor(
    private val repository: RemoteDataRepository
)  {
    suspend operator fun invoke(category:String) : Flow<QuoteItemDto> = flow {
        emit(repository.getQuote(category))
    }
}