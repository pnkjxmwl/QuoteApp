package com.example.quotesapp.presentation.savedquote_sc.components

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.quotesapp.domain.model.Quote
import com.example.quotesapp.presentation.MainViewModel
import com.example.quotesapp.presentation.bottomsheet.BottomSheetContent
import com.example.quotesapp.presentation.bottomsheet.ShimmerBottomSheet
import com.example.quotesapp.util.Result


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SavedQuoteList(
    quotes: List<Quote>,
    viewModel: MainViewModel = hiltViewModel()
) {

    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
    ) {
        items(quotes) { quote ->
            SavedQuoteItem(quote = quote)
        }
    }
    if (viewModel.quote.id != -1) {
        ModalBottomSheet(
            containerColor = Color.White,
            onDismissRequest = {
                if(viewModel.quote.isSaved==false)
                {
                    viewModel.deleteQuote(viewModel.quote)
                }
                viewModel.quote = Quote(quote = "", author = "", category = "", isSaved = false, id = -1)
            },
            sheetState = sheetState,
        ) {
            BottomSheetContent(
                quote = viewModel.quote.quote,
                author = viewModel.quote.author,
                isSaved = viewModel.quote.isSaved,
                onSaveClicked = {
                    viewModel.updateSaved(!viewModel.quote.isSaved)
                },
                showResetIcon = false,
                onNewQuoteClicked = {
                   // viewModel.getQuote(viewModel.quote.category)
                },
            )
        }
    }

}