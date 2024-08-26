package com.example.quotesapp.presentation.category_sc

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.quotesapp.R
//import com.example.quotesapp.data.QuoteDatabase
import com.example.quotesapp.domain.model.Quote
import com.example.quotesapp.domain.repository.LocalDataRepository
//import com.example.quotesapp.domain.model.Quote
import com.example.quotesapp.presentation.MainViewModel
import com.example.quotesapp.presentation.bottomsheet.BottomSheetContent
import com.example.quotesapp.presentation.bottomsheet.ShimmerBottomSheet
import com.example.quotesapp.presentation.category_sc.components.CategoryItem
import com.example.quotesapp.util.Result
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

data class Category(
    val name: String,
    val imageRes: Int // Resource ID of the category image
)

val categoryList = listOf(
    Category(name = "Happiness", imageRes = R.drawable.happiness),
    Category(name = "Inspirational", imageRes = R.drawable.inspirational),
    Category(name = "Funny", imageRes = R.drawable.funny),
    Category(name = "Life", imageRes = R.drawable.life),
    Category(name = "Alone", imageRes = R.drawable.alone),
    Category(name = "Love", imageRes = R.drawable.love),
    Category(name = "Attitude", imageRes = R.drawable.attitude),
    Category(name = "Birthday", imageRes = R.drawable.birthday),
    Category(name = "Failure", imageRes = R.drawable.failure),
    Category(name = "FriendShip", imageRes = R.drawable.friendship),
    )


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryScreen (
    viewModel: MainViewModel = hiltViewModel()
) {

        val sheetState = rememberModalBottomSheetState()
        val scope = rememberCoroutineScope()
        var showBottomSheet by remember { mutableStateOf(false) }
        val context= LocalContext.current
        var currentToast by remember { mutableStateOf<Toast?>(null) }


        var categoryChosen by remember {
            mutableStateOf("")
        }
        val quoteResponse = viewModel.quoteResponse.collectAsState().value

         LazyVerticalGrid(
             columns = GridCells.Adaptive(minSize = 150.dp),
         ) {
             items(categoryList) { category ->
                 CategoryItem(category = category.name,
                     imageRes = category.imageRes,
                     onClick = {
                         categoryChosen = category.name
                         viewModel.getQuote(category.name)
                         showBottomSheet = true
                     }
                 )
             }
         }
         if (showBottomSheet) {
             ModalBottomSheet(
                 containerColor = Color.White,
                 onDismissRequest = {
                     if(viewModel.quote.quote.isNotEmpty() && viewModel.quote.isSaved==true)
                     {
                         viewModel.insertQuote(viewModel.quote)
                     }
                     viewModel.quote = Quote(quote = "", author = "", category = "", isSaved = false, id = -1)
                     showBottomSheet = false
                 },
                 sheetState = sheetState,

             ) {

                 when (quoteResponse) {
                     is Result.Loading -> {
                         ShimmerBottomSheet()
                     }

                     is Result.Error -> {
                         Text(
                             text = quoteResponse.error.localizedMessage ?: "Something went wrong",
                             fontFamily = FontFamily.Monospace,
                             modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                         )
                     }

                     is Result.Success -> {
                         val quoteResult = quoteResponse.data
                         viewModel.quote= Quote(
                             author = quoteResult.author,
                             category = quoteResult.category,
                             quote = quoteResult.quote,
                             isSaved = viewModel.quote.isSaved
                         )
                         Log.d("msg","recomposing")
                         BottomSheetContent(
                             quote = viewModel.quote.quote,
                             author = viewModel.quote.author,
                             isSaved = viewModel.quote.isSaved,
                             onSaveClicked = {
                                 // Cancel the current toast if it is showing
                                 currentToast?.cancel()

                                 // Determine the message to show
                                 val message = if (viewModel.quote.isSaved == false) {
                                     "Added to Favourite"
                                 } else {
                                     "Removed from Favourite"
                                 }

                                 // Show the new Toast
                                 currentToast = Toast.makeText(context, message, Toast.LENGTH_SHORT).apply {
                                     show()
                                 }

                                 viewModel.updateSaved(!viewModel.quote.isSaved)


                             }
                             ,
                             onNewQuoteClicked = {
                                 if(viewModel.quote.quote.isNotEmpty())
                                 {
                                  viewModel.insertQuote(viewModel.quote)
                                 }
                                 viewModel.getQuote(categoryChosen)
                                 viewModel.quote = Quote(quote = "", author = "", category = "", isSaved = false, id = -1)
                             },
                         )
                     }

                     else -> {

                     }
                 }



         }
     }


}


@Preview
@Composable
fun CategoryScreenPreview() {
    CategoryScreen()
}