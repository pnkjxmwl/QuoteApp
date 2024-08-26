package com.example.quotesapp.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quotesapp.data.dto.toQuoteItem
import com.example.quotesapp.domain.QuoteItem
import com.example.quotesapp.domain.model.Quote
import com.example.quotesapp.domain.use_cases.UseCases
import com.example.quotesapp.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val useCases:UseCases
):ViewModel() {

 var quote by mutableStateOf(
        Quote(quote = "", author = "", category = "", id = -1, isSaved = false)
 )
    private val _quoteResponse=
            MutableStateFlow<Result<QuoteItem>>(Result.Idle)
    val quoteResponse= _quoteResponse.asStateFlow()

    private val _savedQuoteResponse=
            MutableStateFlow<Result<List<Quote>>>(Result.Loading)
    val savedQuoteResponse=_savedQuoteResponse.asStateFlow()

    init {
        getAllSavedQuote()
    }
    fun getQuote(
        category:String
    )= viewModelScope.launch {
            useCases.getQuoteUseCase(category)
                .onStart {
                    _quoteResponse.value=Result.Loading
                }
                .catch {
                    _quoteResponse.value=Result.Error(it)
                }
                .collect(){
                    val result= it.toQuoteItem()
                    _quoteResponse.value=Result.Success(result)
                }

    }
    fun getAllSavedQuote() = viewModelScope.launch {

        useCases.getSavedQuoteUseCase()
            .onStart {
                _savedQuoteResponse.value=Result.Loading
            }
            .catch {
                _savedQuoteResponse.value=Result.Error(it)
            }
            .collect(){
                _savedQuoteResponse.value= Result.Success(it)
            }
    }

    fun insertQuote(quote: Quote){
        viewModelScope.launch {
            useCases.insertSavedQuoteUseCase(quote)
        }
    }

    fun deleteQuote(quote: Quote){
        viewModelScope.launch {
            useCases.deleteSavedQuoteUseCase(quote)
        }
    }

    fun updateSaved(saved:Boolean){
        quote= quote.copy(
            isSaved = saved
        )
    }



}