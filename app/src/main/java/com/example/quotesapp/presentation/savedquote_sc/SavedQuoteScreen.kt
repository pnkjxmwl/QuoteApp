package com.example.quotesapp.presentation.savedquote_sc

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.quotesapp.presentation.MainViewModel
import com.example.quotesapp.presentation.savedquote_sc.components.EmptyListScreen
import com.example.quotesapp.presentation.savedquote_sc.components.LoadingAndErrorScreen
import com.example.quotesapp.presentation.savedquote_sc.components.SavedQuoteList
import com.example.quotesapp.util.Result

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SavedQuoteScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel()
) {

    val savedQuoteResponse by viewModel.savedQuoteResponse.collectAsStateWithLifecycle()

        AnimatedContent(
            modifier = Modifier
                .fillMaxSize(),
            targetState = savedQuoteResponse,
            label = "animated content",
            transitionSpec = {
                fadeIn(
                    animationSpec = tween(
                        300,
                        easing = LinearEasing
                    )
                ) togetherWith fadeOut(
                    animationSpec = tween(
                        300,
                        easing = LinearEasing
                    )
                )

            }
        ) { result ->
            when (result) {

                is Result.Loading -> {
                    LoadingAndErrorScreen(label = "Loading ...")
                }

                is Result.Error -> {
                    val msg = result.error.message ?: "Something went wrong"
                    LoadingAndErrorScreen(label = msg)
                }

                is Result.Success -> {
                    val quotes = result.data
                    if (quotes.isEmpty()) {
                        EmptyListScreen()
                    } else
                        SavedQuoteList(quotes = quotes)


                }

                else -> Unit


            }

        }




}

